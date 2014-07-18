/**
 * Created by Duane on 7/4/2014.
 */
window.onload=function(){
    function countdown(){


        if (typeof countdown.counter == 'undefined'){
            countdown.counter = 5;
        }
        if (countdown.counter > 0) {
            document.getElementById('count').innerHTML = countdown.counter--;
            setTimeout(countdown, 1000);
        }
        else
            location.href = '../pages/login.jsp';
    }
    countdown();
};