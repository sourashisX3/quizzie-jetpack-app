# Quizzie - Jetpack Compose Quiz App

A modern Android quiz application built with Jetpack Compose and Clean Architecture principles.

## 🛠️ Tech Stack

### Core Technologies
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Dependency Injection:** Hilt (Dagger)
- **Architecture:** Clean Architecture with MVVM

### Key Libraries
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material Design 3** - Latest Material Design components
- **Navigation Compose** - Type-safe navigation
- **Constraint Layout Compose** - Advanced layout positioning
- **Coil** - Image loading library
- **Hilt** - Dependency injection
- **KSP** - Kotlin Symbol Processing for annotation processing

## 📱 Features

### User Authentication
- Login Screen
- Signup Screen
- Forgot Password functionality
- Portrait & Landscape layouts

### Main Features
- **Home** - Browse quiz categories and recently played quizzes
- **Contest** - Participate in quiz contests
- **Leaderboard** - View rankings and scores
- **Profile** - User profile management
- **Onboarding** - First-time user experience

## 🏗️ Project Structure

```
app/
├── core/
│   ├── constants/       # App-wide constants
│   ├── navigation/      # Navigation setup
│   └── shared/          # Reusable components & utilities
├── features/
│   ├── auth/           # Authentication screens
│   ├── home/           # Home screen with quiz categories
│   ├── contest/        # Contest feature
│   ├── leaderboard/    # Leaderboard display
│   ├── profile/        # User profile
│   ├── navbar/         # Bottom navigation
│   └── onboarding/     # Onboarding screens
└── ui/
    └── theme/          # App theme, colors, typography
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug or higher
- JDK 11 or higher
- Minimum SDK: 26 (Android 8.0)
- Target SDK: 36

### Installation

1. Clone the repository:
```bash
git clone https://github.com/sourashisX3/quizzie-jetpack-app.git
cd quizzie-jetpack-app
```

2. Open the project in Android Studio

3. Sync Gradle files

4. Run the app on an emulator or physical device

## 📦 Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| Kotlin | 2.0.21 | Programming language |
| Compose BOM | 2024.09.00 | Jetpack Compose libraries |
| Hilt | 2.51.1 | Dependency injection |
| Navigation Compose | 2.7.3 | Screen navigation |
| Coil | 2.7.0 | Image loading |
| Material3 | Latest | UI components |

## 🎨 UI/UX
- Material Design 3 guidelines
- Dark and light theme support
- Responsive layouts (Portrait & Landscape)
- Custom fonts: Belanosima & Chango
- Edge-to-edge experience

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

**Sourashis**
- GitHub: [@sourashisX3](https://github.com/sourashisX3)

---

Made with ❤️ using Jetpack Compose

