window.onload = function () {
    const canvasDrawer = new CanvasDrawer(sendRequest, validateR);
    console.log("Я ВЕСЬ ТАКОЙ ПЕРЕЗАГРУЗИЛСЯ")

    window.canvasDrawer = canvasDrawer;

    canvasDrawer.redrawAll(sessionStorage.getItem("R") ? parseInt(sessionStorage.getItem("R")) : 1, getPoints());

    window.handleR = function (value) {
        console.log("im into handle r")

        sessionStorage.setItem("R", value);
        canvasDrawer.redrawAll(value, getPoints());

    };
    window.handleSubmit = function (data) {
        console.log(data)
        console.log(data.responseCode)
        if (data.responseCode === 400) {
            processError(data)
        } else if (data.status === "success") {
            const x = getX();
            const y = getY();
            const result = getResult();

            if (validateY() && validateR()) {
                console.log("got some values:", x, y, sessionStorage.getItem("R"), result);
                canvasDrawer.drawBOOM(x, y);
                setTimeout(()=>{
                    canvasDrawer.redrawAll(parseInt(sessionStorage.getItem("R")), getPoints())
                }, 300)
            }
        }

    }

    function getX() {
        const table = document.getElementById("table");
        row = table.rows[1]
        console.log(row);
        console.log(row.cells[0].textContent.trim());
        x = row.cells[0].textContent.trim()
        return x;

    }
    function getY() {
        const table = document.getElementById("table");
        row = table.rows[1]
        console.log(row);
        console.log(row.cells[1].textContent.trim());
        y = row.cells[1].textContent.trim()

        return y;

    }

    function getResult() {
        const table = document.getElementById("table");
        row = table.rows[1]
        console.log(row);
        console.log(row.cells[3].textContent.trim());
        result = row.cells[3].textContent.trim()

        return result === "TRUE";
    }

    const yText = document.getElementById("inputForm:y");

    yText.addEventListener("focus", validateY);
    yText.addEventListener("input", () => {
        validateY();
        let tmp = yText.value;
        let foundDot = false;
        if (!tmp) return;
        y = "";
        let negative = false;
        for (let i = 0; i < tmp.length; i++) {
            if (tmp[i] === '-' && i === 0) {
                negative = true;
            } else if (tmp[i] === '.' && !foundDot) {
                foundDot = true;
            } else if (isNaN(parseFloat(tmp[i]))) continue;
            y += tmp[i];
        }
        if (y.length > 1 && y[1] !== '.' && y[0] === '0') {
            y = y.substring(1);
        }
        while (y.includes('-0') && !y.includes('.')) {
            y = y.replace('-0', '-');
        }
        yText.value = y;
    });


    const links = document.querySelectorAll(".r-links a"); // Select all links in the panelGrid

    links.forEach(link => {
        link.addEventListener("click", function (event) {
            // Remove the 'active-link' class from all links
            links.forEach(l => l.classList.remove("active-link"));

            // Add the 'active-link' class to the clicked link
            event.target.classList.add("active-link");
        });
    });

    document.getElementById("clearButton").addEventListener("click", ()=>{

        canvasDrawer.redrawAll(getR(), [])
    })

    function sendRequest(x, y) {

        console.log("даа я получил значения из канваса вот они " + x + " "+ y)
        document.getElementById("inputForm:x-from-canvas").value = x
        document.getElementById("inputForm:y-from-canvas").value = y;

        document.getElementById('inputForm:x-from-canvas').dispatchEvent(new Event('change'));
        document.getElementById('inputForm:y-from-canvas').dispatchEvent(new Event('change'))

       document.getElementById("checkButton").click();

    }

    function processError(data) {
        alert(JSON.parse(data.responseText).error)
    }

    function getPoints() {
        const table = document.getElementById("table");
        let points = []
        for (let i = 1; i < table.rows.length; i++) {
            row = table.rows[i]
                let rad_scl =  sessionStorage.getItem("R") ? sessionStorage.getItem("R") / parseInt(row.cells[2].textContent.trim()) : 1
                points.push({
                    "x": parseFloat(row.cells[0].textContent.trim().replaceAll(",", ".")) * rad_scl,
                    "y": parseFloat(row.cells[1].textContent.trim().replaceAll(",", ".")) * rad_scl,
                    "result": row.cells[3].textContent.trim() === "TRUE"
                })
            }

        return points

    }

    function validateR() {
        if (sessionStorage.getItem("R") == null) {
            alert("ВЫБЕРИТЕ РАДИУС пж)")
            return false;
        }
        return true;
    }

    function getR(){
        return sessionStorage.getItem("R") ? parseInt(sessionStorage.getItem("R")) : 1
    }
}
