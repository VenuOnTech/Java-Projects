function appendValue(value) {
    document.getElementById('display').value += value;
}

function clearDisplay() {
    document.getElementById('display').value = '';
}

function calculate() {
    let expression = document.getElementById('display').value;
    try {
        expression = expression
            .replace(/(\d+)!/g, (_, n) => `fact(${n})`)
            .replace(/(\d+)\^(\d+)/g, (_, base, exp) => `Math.pow(${base}, ${exp})`)
            .replace(/sin\(/g, 'Math.sin(toRad(')
            .replace(/cos\(/g, 'Math.cos(toRad(')
            .replace(/tan\(/g, 'Math.tan(toRad(')
            .replace(/asin\(/g, 'toDeg(Math.asin(')
            .replace(/acos\(/g, 'toDeg(Math.acos(')
            .replace(/atan\(/g, 'toDeg(Math.atan(')
            .replace(/log\(/g, 'Math.log10(')
            .replace(/ln\(/g, 'Math.log(')
            .replace(/sqrt\(/g, 'Math.sqrt(')
            .replace(/cbrt\(/g, 'Math.cbrt(')
            .replace(/exp\(/g, 'Math.exp(')
            .replace(/mod\(/g, 'mod(');

        const result = eval(expression);
        document.getElementById('display').value = result;
        addToHistory(expression, result);
    } catch (e) {
        document.getElementById('display').value = 'Error';
    }
}

function fact(n) {
    if (n < 0) return NaN;
    if (n === 0 || n === 1) return 1;
    return n * fact(n - 1);
}

function mod(a, b) {
    return a % b;
}

function toRad(deg) {
    return deg * Math.PI / 180;
}

function toDeg(rad) {
    return rad * 180 / Math.PI;
}

function addToHistory(expr, result) {
    const historyList = document.getElementById("historyList");
    const li = document.createElement("li");
    li.textContent = `${expr} = ${result}`;
    li.onclick = () => {
        document.getElementById("display").value = expr;
    };
    historyList.prepend(li);
}

function clearHistory() {
    document.getElementById("historyList").innerHTML = '';
}

function toggleHistory() {
    const panel = document.getElementById("historyPanel");
    panel.style.display = panel.style.display === "none" ? "block" : "none";
}
