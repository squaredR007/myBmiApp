# 📱 BMI Calculator

A clean, modern BMI (Body Mass Index) calculator for Android — my first Android app, built from scratch in Android Studio using Kotlin and XML layouts.

![Platform](https://img.shields.io/badge/platform-Android-3DDC84?logo=android&logoColor=white)
![Language](https://img.shields.io/badge/language-Kotlin-7F52FF?logo=kotlin&logoColor=white)
![Min SDK](https://img.shields.io/badge/minSdk-24-blue)

<!--
  📸 Add screenshots here once you have them, e.g.:
  <p align="center">
    <img src="screenshots/input_screen.png" width="250" />
    <img src="screenshots/result_screen.png" width="250" />
  </p>
-->

## ✨ Features

- **Metric & Imperial support** — switch between kg/m and lb/in with a single tap
- **Instant BMI calculation** with input validation (no crashes on empty or invalid input)
- **Visual BMI result bar** — a custom-drawn gradient gauge showing exactly where your result falls across Underweight, Normal, Overweight, and Obese ranges
- **Clean, modern UI** — Material Design 3 theming with a soft pastel color palette, rounded components, and the Poppins typeface throughout
- **State-safe** — unit selection survives screen rotation via `onSaveInstanceState`

## 🛠️ Built With

- **Kotlin** — app logic
- **XML + ConstraintLayout** — UI layouts
- **Material Components for Android (Material3)** — theming, buttons, text fields
- **Custom `View` + `Canvas`** — the hand-drawn BMI result gauge (`BmiBarView`)

## 📐 How It Works

The app is built around two screens:

1. **Input screen** — collects weight and height (with live unit switching between metric and imperial), validates the input, and calculates BMI using the standard formula:
   - Metric: `BMI = weight(kg) / height(m)²`
   - Imperial: `BMI = 703 × weight(lb) / height(in)²`
2. **Result screen** — displays the calculated BMI alongside its category (Underweight / Normal / Overweight / Obese), visualized on a custom gradient bar built with a hand-rolled `View` subclass that draws directly to a `Canvas`.

## 🚀 Getting Started

### Prerequisites
- [Android Studio](https://developer.android.com/studio) (recent stable version)
- An emulator or physical Android device running **API 24 (Android 7.0)** or higher

### Installation
```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
```
1. Open the project in Android Studio
2. Let Gradle sync finish
3. Run the app on an emulator or connected device

## 📂 Project Structure

```
app/src/main/
├── java/com/example/mybmiapp/
│   ├── MainActivity.kt        # Input screen — validation, unit toggle, BMI calculation
│   ├── MainActivity2.kt       # Result screen — displays BMI + category
│   └── BmiBarView.kt          # Custom View: draws the gradient result gauge
└── res/
    ├── layout/
    │   ├── activity_main.xml   # Input screen layout
    │   └── activity_main2.xml  # Result screen layout
    ├── values/
    │   ├── colors.xml          # App color palette
    │   └── themes.xml          # Material3 theme (light)
    ├── values-night/
    │   └── themes.xml          # Material3 theme (dark)
    └── font/
        └── poppins*.xml        # Poppins typeface
```

## 🗺️ Roadmap

This project is being actively developed in phases. Planned additions:

- [ ] Animated BMI reveal (counting number + animated gauge marker)
- [ ] BMI history tracking with local storage (Room database)
- [ ] Trend chart of BMI over time
- [ ] Refined dark mode palette
- [ ] Unit tests for BMI calculation logic

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🙋 About

This is my first Android app, built as a learning project to get hands-on with Kotlin, Android's Activity lifecycle, Material Design, and custom view drawing. Feedback and suggestions are welcome!
