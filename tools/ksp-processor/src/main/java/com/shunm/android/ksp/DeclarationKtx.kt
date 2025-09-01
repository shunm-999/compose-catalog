package com.shunm.android.ksp

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeArgument

/**
 * classDecl が Generics<X> を実装していれば X の KSType を返す。
 * 見つからなければ null。
 *
 * @param resolver Resolver（FQN 照合に使用）
 * @param generics Generics の完全修飾名（例: "com.example.Provider"）
 */
fun KSClassDeclaration.genericTypeArgOrNull(
    resolver: Resolver,
    generics: String,
): KSType? {
    val genericsDecl = resolver.getClassDeclarationByName(
        resolver.getKSNameFromString(generics),
    ) ?: return null

    // class の全 super type を走査
    superTypes.forEach { superRef ->
        val superType = superRef.resolve()
        val superDecl = superType.declaration as? KSClassDeclaration ?: return@forEach

        if (superDecl.qualifiedName?.asString() == genericsDecl.qualifiedName?.asString()) {
            val argRef = superType.arguments.firstOrNull()?.type ?: return null
            return argRef.resolve()
        }
    }

    return null
}

fun KSType.deepNonNull(resolver: Resolver): KSType {
    val base = this.makeNotNullable()
    if (base.arguments.isEmpty()) return base

    val newArgs: List<KSTypeArgument> = base.arguments.mapNotNull { arg ->
        val t = arg.type?.resolve()
        val normalized = t?.deepNonNull(resolver)
        val typeRef = normalized?.let { resolver.createKSTypeReferenceFromKSType(it) }
            ?: return@mapNotNull null
        resolver.getTypeArgument(typeRef, arg.variance)
    }
    return base.replace(newArgs)
}

fun KSType.isDeepNullable(): Boolean {
    if (this.isMarkedNullable) {
        return true
    }
    for (arg in arguments) {
        val innerType = arg.type?.resolve() ?: continue
        if (innerType.isDeepNullable()) return true
    }
    return false
}

fun KSType.equalsIgnoringNullabilityDeep(other: KSType, resolver: Resolver): Boolean = this.deepNonNull(resolver) == other.deepNonNull(resolver)

fun KSType.isAssignableFromIgnoringNullabilityDeep(other: KSType, resolver: Resolver): Boolean = this.deepNonNull(resolver).isAssignableFrom(other.deepNonNull(resolver))
