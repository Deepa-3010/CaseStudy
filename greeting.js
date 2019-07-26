(function(){
    function greet(){
    const random = require('random');
    greeting=['Hii,help us to suggest you the required monitoring devices',"Welcome! Find the Monitoring Devices you need ",
    "Hello, We are here to suggest you monitoring devices "];

    console.log("\n-----------------------");
    console.log(greeting[random.int(min = 0, max = 2)]);
    }
    module.exports={greetFun:greet};
})();
 
