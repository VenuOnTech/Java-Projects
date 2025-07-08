# 🔢 Scientific Calculator Project

This project is a dual-version **Scientific Calculator** implemented in:

1. 🖥️ **Java (Console-based)** — with a custom-built expression parser using the **Shunting Yard Algorithm** (no external libraries).
2. 🌐 **Web (HTML, CSS, JavaScript)** — fully interactive scientific calculator with keyboard input, history panel, and custom UI.

---

## 🧮 Features

Supported operations in both versions:

- ✅ Basic arithmetic: `+`, `-`, `*`, `/`
- ✅ Trigonometric functions: `sin`, `cos`, `tan`
- ✅ Inverse trig: `asin`, `acos`, `atan`
- ✅ Logarithmic: `log`, `ln`
- ✅ Exponential functions: `exp(x)`
- ✅ Power & roots: `^`, `sqrt`, `cbrt`, nth power/root
- ✅ Factorial: `n!`
- ✅ Modulus (in web version): `mod(a, b)`
- ✅ Expression parsing with full bracket support

---

## 📁 Project Structure

### 🖥 Java Calculator (Without Library)

- File: `CalculatorWithoutLibrary.java`
- Core Logic:
  - Custom expression tokenizer using regex
  - Conversion to postfix using **Shunting Yard Algorithm**
  - Evaluation of postfix expressions using a stack
  - Support for basic functions like `sin`, `log`, `fact`, etc.

### 🌐 Web Calculator

- `WebCalci.html` — Calculator layout using HTML
- `Web_Calci_Styles.css` — Clean dark-themed UI, responsive and modern
- `WebCalci_Functions.js` — Evaluates expressions, manages keyboard input and history

---

## 🌐 Web-Specific Features

- 🧠 Type using both **keyboard** and **buttons**
- 🖼️ Dark-themed user interface
- 📜 **Floating History Button**
- 🧾 **Right-side history panel**
- 🔁 Click-to-reuse old expressions
- 🧹 **Clear History** option
- 📱 Fully **responsive design** for mobile/tablet use

---

## 🚀 How to Run

### Java Version
```bash
javac Calculator.java
java Calculator
