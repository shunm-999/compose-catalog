package com.shunm.android.presentation.component.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import com.shunm.android.compose_catalog.component.ComponentRes
import com.shunm.android.compose_catalog.component.pixnio_6000x4000
import com.shunm.android.presentation.component.di.CatalogProvider
import com.shunm.android.presentation.component.di.Provider
import org.jetbrains.compose.resources.painterResource

@CatalogProvider
internal class ListItemLeadingProvider : Provider<@Composable (ListItemLeadingScope.() -> Unit)> {
    override fun provide(): List<@Composable (ListItemLeadingScope.() -> Unit)> {
        return listOf(
            {
                LeadingIcon(
                    imageVector = Icons.Default.Person
                )
            },
            {
                LeadingAvatar(
                    imageVector = Icons.Default.Person
                )
            },
            {
                LeadingAvatar(
                    text = "A"
                )
            },
            {
                LeadingImage(
                    painter = painterResource(ComponentRes.drawable.pixnio_6000x4000)
                )
            },
            {
                LeadingImage(
                    url = "https://images.dog.ceo/breeds/hound-english/n02089973_1.jpg"
                )
            },
            {
                LeadingVideoThumbnail(
                    painter = painterResource(ComponentRes.drawable.pixnio_6000x4000)
                )
            },
            {
                LeadingVideoThumbnail(
                    url = "https://images.dog.ceo/breeds/hound-english/n02089973_1.jpg"
                )
            },
        )
    }
}
