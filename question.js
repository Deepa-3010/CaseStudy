const questionHelper = require('./questionHelper');

var questionArray = ["Press 1:Display devices based on Touch\nPress 2:Display devices by Screen Size\n", "Press 1:Touch screen\nPress 2:Non-Touch screen \nPress 3:To go to the previous menu\n", "Enter the size\n",
 "Press 1:Display devices only by touch\nPress 2:Filter by Screen Size\nPress 3:To go to the previous menu\n",
 "Press 1:Display devices only by screen size\nPress 2:Filter by touch\nPress 3:To go to the previous menu\n"];


var _touch, _size;
var touchIfSelected = false;

let checkBeginFunctionExport=require("./modules")
let displayAgentExport = require("./displayDevices");
var questionIndex=0;
async function beginQuestion() {

    console.log("\n-----------------------")
    var answer1 = await questionHelper.askQuestion(questionArray[questionIndex]);
    console.log("\n-----------------------")
    if (parseInt(answer1) == 0) {
        checkBeginFunctionExport.exitMessage();
    }
    if (parseInt(answer1) == 1)
        touchSelected();
    if (parseInt(answer1) == 2)
        sizeSelected();
    if (answer1 != 1 && answer1 != 2) {
        console.log("You have entered wrong input");
        beginQuestion();
    }
}
async function touchSelected(questionIndex) {
    touchIfSelected = true;
    var answer2 = await questionHelper.askQuestion(questionArray[1]);
    if (parseInt(answer2) == 0) {
        checkBeginFunctionExport.exitMessage();

    }
    else if (parseInt(answer2)==3){

        beginQuestion(questionIndex);

    }
    else {
         questionIndex=1;
        console.log("\n-----------------------")
        _touch = touch(answer2);

        var answer3 = await questionHelper.askQuestion(questionArray[3]);
        if (parseInt(answer3) == 0) {
            checkBeginFunctionExport.exitMessage();

        }
        else if (parseInt(answer3)==3)
        {
            touchSelected(questionIndex);
        }
        else {
            console.log("\n-----------------------")
            if (parseInt(answer3) == 1) {
                displayAgentExport.displayTouch(global, _touch);
            }
            if (parseInt(answer3) == 2) {
                var answer4 = await questionHelper.askQuestion(questionArray[2]);
                if (parseInt(answer4) == 0) {
                    checkBeginFunctionExport.exitMessage();

                }
                else {
                    console.log("\n-----------------------")
                    _size = answer4;
                    displayAgentExport.display(global, _touch, _size);
                }
            }
        }
    }
}

async function sizeSelected(questionIndex) {

    var answer2 = await questionHelper.askQuestion(questionArray[2]);
    if (parseInt(answer2) == 0) {
        checkBeginFunctionExport.exitMessage();

    }
    else {
        console.log("\n-----------------------")
        _size = answer2;

        if (touchIfSelected == true) {
            displayAgentExport.display(global, _touch, _size);
        }
        else {
            questionIndex=2;
            var answer3 = await questionHelper.askQuestion(questionArray[4]);
            if (parseInt(answer3) == 0) {
                checkBeginFunctionExport.exitMessage();

            }
            else {
                console.log("\n-----------------------")
                if (answer3 == 1) {
                    displayAgentExport.displaySize(global, _size);
                }
                else if(parseInt(answer3)==3)
                {
                    sizeSelected(questionIndex);
                }

                else if (answer3 == 2) {
                    var answer4 = await questionHelper.askQuestion(questionArray[1]);
                    if (parseInt(answer4) == 0) {
                        checkBeginFunctionExport.exitMessage();

                    }
                    else if(parseInt(answer4)==3)
                    {

                        sizeSelected(questionIndex);
                    }
                    else {
                        console.log("\n-----------------------")
                        _touch = touch(answer4);
                        displayAgentExport.display(global, _touch, _size);
                    }
                }
            }
        }
    }
}

function touch(answer) {
    if (answer == 0) {
        console.log("Thank You");
        process.exit();
    }
    if (answer == 1)
        return true;
    if (answer == 2)
        return false;
    if (answer != 1 && answer != 2) {
        console.log("You have entered a wrong input try again from the start");
        beginQuestion();
    }

}
module.exports = { questioning: beginQuestion};



//module.exports={questioning:beginQuestion};
