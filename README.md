# Compose Catalog

A Kotlin Multiplatform application showcasing various Jetpack Compose components and UI patterns. This project demonstrates modern Android development practices with support for both Android and iOS platforms.

## Features

- **Multiplatform Support**: Runs on Android and iOS using Kotlin Multiplatform and Compose Multiplatform
- **Component Catalog**: Comprehensive collection of reusable UI components
- **GitHub Integration**: Demonstrates API integration with GitHub user listing
- **Clean Architecture**: Follows clean architecture principles with domain, presentation, and infrastructure layers
- **Dependency Injection**: Uses Hilt for dependency management
- **Testing**: Includes unit tests and screenshot tests with Roborazzi

## Architecture

The project follows a modular clean architecture:

```
├── composeApp/          # Main application module
├── domain/              # Business logic and entities
│   ├── category/        # Category domain models
│   ├── component/       # Component domain models
│   ├── github/          # GitHub-related domain logic
│   └── shared/          # Shared domain utilities
├── presentation/        # UI layer
│   ├── component/       # Reusable UI components
│   ├── github/          # GitHub feature screens
│   └── shared/          # Shared presentation utilities
├── infra/               # Infrastructure layer
│   └── github/          # GitHub API implementation
└── tools/               # Build tools and processors
    └── ksp-processor/   # Kotlin Symbol Processing utilities
```

## Tech Stack

- **Language**: Kotlin 2.2.10
- **UI Framework**: Compose Multiplatform 1.8.2
- **Architecture**: Clean Architecture with MVVM
- **Dependency Injection**: Hilt 2.57.1
- **Networking**: Retrofit 3.0.0 with Ktor 3.2.3
- **Image Loading**: Coil 3.3.0
- **Testing**: Roborazzi 1.50.0 for screenshot testing
- **Build System**: Gradle with Version Catalogs

## Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- JDK 17
- Xcode 15+ (for iOS development)

### Setup

1. Clone the repository:
```bash
git clone https://github.com/shunm-999/compose-catalog.git
cd compose-catalog
```

2. Open the project in Android Studio

3. Sync the project to download dependencies

4. Run on Android:
   - Select the `composeApp` configuration
   - Choose your target device/emulator
   - Click Run

5. Run on iOS:
   - Open `iosApp/iosApp.xcodeproj` in Xcode
   - Select your target device/simulator
   - Click Run

## Project Structure

### Modules

- **composeApp**: Main application entry point with platform-specific implementations
- **domain**: Business logic modules containing use cases, repositories, and entities
- **presentation**: UI components and screens organized by feature
- **infra**: Infrastructure implementations including API clients and repositories
- **gradle-conventions**: Build configuration and shared Gradle logic
- **tools**: Build-time tools including KSP processors for code generation

### Key Components

The project includes a comprehensive set of UI components:

- App bars and navigation bars
- Buttons, chips, and badges
- Cards and list items
- Charts and graphs
- Dialogs and snackbars
- Form controls (text fields, checkboxes, sliders)
- And many more...

## Building

### Android
```bash
./gradlew assembleDebug
```

### iOS
```bash
./gradlew iosSimulatorArm64Test
```

### Run Tests
```bash
./gradlew test
```

### Screenshot Tests
```bash
./gradlew recordRoborazziDebug
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Ensure all tests pass
6. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For questions or feedback, please open an issue on the GitHub repository.