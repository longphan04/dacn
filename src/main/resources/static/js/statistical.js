// function changeTab(evt, type) {
//     // Declare all variables
//     var i, tabcontent, tablinks;
//
//     // Get all elements with class="tabcontent" and hide them
//     tabcontent = document.getElementsByClassName("tabcontent");
//     for (i = 0; i < tabcontent.length; i++) {
//         tabcontent[i].style.display = "none";
//     }
//
//     // Get all elements with class="tablinks" and remove the class "active"
//     tablinks = document.getElementsByClassName("tablinks");
//     for (i = 0; i < tablinks.length; i++) {
//         tablinks[i].className = tablinks[i].className.replace(" active", "");
//     }
//
//     // Show the current tab, and add an "active" class to the button that opened the tab
//     document.getElementById(type).style.display = "block";
//     evt.currentTarget.className += " active";
// }
//
// function toggleType(evt, type) {
//     // Declare all variables
//     var i, tabcontent, tablinks;
//
//     // Get all elements with class="tabcontent" and hide them
//     tabcontent = document.getElementsByClassName("tabcontent");
//     for (i = 0; i < tabcontent.length; i++) {
//         tabcontent[i].style.display = "none";
//     }
//
//     // Get all elements with class="tablinks" and remove the class "active"
//     tablinks = document.getElementsByClassName("tablinks");
//     for (i = 0; i < tablinks.length; i++) {
//         tablinks[i].className = tablinks[i].className.replace(" active", "");
//     }
//
//     // Show the current tab, and add an "active" class to the button that opened the tab
//     document.getElementById(type).style.display = "block";
//     evt.currentTarget.className += " active";
// }
//
// function changeTab(event, tabName) {
//     const tabcontent = document.getElementsByClassName("tabcontent");
//     for (let i = 0; i < tabcontent.length; i++) {
//         tabcontent[i].style.display = "none";
//     }
//     const tablinks = document.getElementsByClassName("tablinks");
//     for (let i = 0; i < tablinks.length; i++) {
//         tablinks[i].className = tablinks[i].className.replace(" active", "");
//     }
//     event.currentTarget.className += " active";
// }

function changeTab(tabName) {
    const tabcontent = document.getElementsByClassName("tabcontent");
    for (let i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    const tablinks = document.querySelectorAll(".tablinks[onclick^='changeTab']");
    for (let i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.querySelector(`#${tabName}Tab`).style.display = "block";
    document.querySelector(`#${tabName}Btn`).className += " active";
}


function toggleType(event, type) {
    const tabcontent = document.getElementsByClassName("tabcontent child");
    for (let i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    const tablinks = document.querySelectorAll(".tablinks[onclick^='toggleType']");
    for (let i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(type).style.display = "block";
    event.currentTarget.className += " active";
}

function submitMonthlyForm() {
    let date = document.querySelector("#monthlyCalendar").value;
    document.querySelector("#monthlyYear").value = date.split("-")[0];
    document.querySelector("#monthlyMonth").value = date.split("-")[1];
    document.querySelector("#monthlyForm").submit();
}

function submitAnnualForm() {
    document.querySelector("#annualDate").value = document.querySelector("#annualCalendar").value;
    document.querySelector("#annualForm").submit();
}

$(document).ready(function () {
    $('#annualCalendar').yearpicker();
});






