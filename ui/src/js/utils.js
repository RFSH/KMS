/*
 msg = message to be shown
 type= type of message(error, success, warning, notice)
 is_stickey = True or False (default=False)
 duration = duration of message (default=
 */
function show_message(msg, type, is_sticky, duration) {
    if (!duration)duration = 3000;
    var t = $().toastmessage({
        text: msg,
        sticky: is_sticky,
        type: type,
        position: 'top-left',
        stayTime: duration
    });
    $().toastmessage('showToast', t);
}

