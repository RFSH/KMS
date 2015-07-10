(function () {
    var hooked = false;
    var hookListeners = [];

    window.javangHook = function () {
        hooked = true;
        while (hookListeners.length) {
            var callback = hookListeners.pop();
            callback();
        }
    };

    window.javangBridge = {};

    window.registerScopeFunction = function (scope, functionName, func) {
        scope['functionName'] = func.apply;
    };

    window.onHook = function (callback) {
        if (hooked) {
            callback();
        } else {
            hookListeners.push(callback);
        }
    };

    window.changePage = function (page) {
        window.location.hash = page;
    }
})();


var app = angular.module("ng-java", []);

app.service("$ngJava", function() {
    var callbacks = [];
    this.ready = function (callback) {
        callbacks.push(callback);
    };

    this.signalReady = function() {
        for (var i = 0; i < callbacks.length; i++) {
            callbacks[i]();
        }
    };
});

app.directive("ngJavaController", function ($ngJava) {
    function link (localScope, el, attrs) {
        var functionWrappers = {
        };
        onHook(function () {
            //javangBridge.registerController(localScope.ngJavaController, localScope, functionWrappers);
            javangBridge.registerController(attrs.ngJavaController, localScope, functionWrappers);
            for (key in functionWrappers) {
                localScope[key] = function() {
                    return functionWrappers[key].invoke.apply(functionWrappers[key], arguments);
                };
            }
            $ngJava.signalReady();
        });
    }

    return {
        link: link
        //scope: {
        //    ngJavaController: '@'
        //}
    };
});

// Testing

