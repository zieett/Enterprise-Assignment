$(document).on("click", ".slide-img", function() {
    let name = $(this).parent().find("div").text()
    let imgFound = false;
    let data = localStorage.getItem("data")
    let index;
    for (let i = 0; i < data.split("\n").length -1 ; i++) {
        if (data.split("\n")[i].split("&&")[1].includes(name)){
            index = i
            imgFound =true
        }
    }
    console.log(imgFound)
    let drug = {
        id: data.split("\n")[index].split("&&")[0],
        name: data.split("\n")[index].split("&&")[1],
        stock: data.split("\n")[index].split("&&")[10],
        price: data.split("\n")[index].split("&&")[9],
        preparation: data.split("\n")[index].split("&&")[2],
        packaging: data.split("\n")[index].split("&&")[3],
        dosage: data.split("\n")[index].split("&&")[5],
        ingredients: data.split("\n")[index].split("&&")[7],
        country: data.split("\n")[index].split("&&")[8],
        group: data.split("\n")[index].split("&&")[4],
        type: data.split("\n")[index].split("&&")[6],
        producers_id: data.split("\n")[index].split("&&")[11]
    }
    localStorage.setItem("drug-click", JSON.stringify(drug))
    window.location.href = "http://localhost:8080/drug-information.html"
})