

// function checkBeginFunction(answer1){
//     if (parseInt(answer1) == 0) {
//         console.log("Thank you");
//         process.exit();
//     }
//     if (parseInt(answer1) == 1)
//         questionExport.touchSelected();
//     if (parseInt(answer1) == 2)
//         questionExport.sizeSelected();
//     if (answer1 != 1 && answer1 != 2) {
//         console.log("You have entered wrong input");
//         beginQuestion();
//     }
// }
// (function(){
//     let questionExport=require("./question");
//     questionExport.questioning()


// })();

function exitMessage()
{
    console.log("Thank You !");
    process.exit();
}

module.exports={exitMessage:exitMessage};