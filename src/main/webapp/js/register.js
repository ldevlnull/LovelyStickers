/**
 * Surely there's some improvements to make, comments/help always appreciated :3
 */
var numbertab = 0;
function init() {
    // Generate li foreach fieldset
    for (var i = 0; i < count; i++) {
        var ul = document.querySelector('ul.items'),
            li = document.createElement("li");

        ul.appendChild(li);
    }
    // Add class active on first li
    ul.firstChild.classList.add('active');
}
function next(target) {
    var input = target.previousElementSibling;
    var correct = false;
    $.ajax({
        url: "getUsers",
        method: "get",
        success: function (users) {
            if (input.value === '') {
                body.classList.add('error');
            } else {
                for (var user in users) {
                    if (input.value == users[user].username || input.value == users[user].email) {
                        correct = false;
                        body.classList.add('error');
                        break;
                    } else {
                        correct = true;
                    }
                }
                if (correct) {
                    numbertab++;
                    alert(numbertab);


                    body.classList.remove('error');

                    var enable = document.querySelector('form fieldset.enable'),
                        nextEnable = enable.nextElementSibling;
                    enable.classList.remove('enable');
                    enable.classList.add('disable');
                    nextEnable.classList.add('enable');

                    // Switch active class on left list
                    var active = document.querySelector('ul.items li.active'),
                        nextActive = active.nextElementSibling;
                    active.classList.remove('active');
                    nextActive.classList.add('active');
                }
            }
            if(numbertab==3){
                register();
            }
        }
    });
}
function register() {
    alert("Hello from register");
    var user = {
        username: $('#username').val(),
        email: $('#email').val(),
        password: $('#password').val()
    }
    $.ajax({
        url: "/newUser?" + $('input[name=csrf_name]').val() +"=" + $('input[name=csrf_value]').val(),
        contentType: "application/json; charset=UTF-8",
        type: "POST",
        data: JSON.stringify(user),
        success: function () {
            alert("Лист з інформацією про реєстраію був надісланний на електронну пошту");
        }
    });
}
function keyDown(event) {
    var key = event.keyCode,
        target = document.querySelector('fieldset.enable .button');
    if (key == 13 || key == 9) next(target);
}
var body = document.querySelector('body'),
    form = document.querySelector('form'),
    count = form.querySelectorAll('fieldset').length;

window.onload = init;
document.body.onmouseup = function (event) {
    var target = event.target || event.toElement;
    if (target.classList.contains("button")) next(target);
};
document.addEventListener("keydown", keyDown, false);