const readline = require('readline');
const r1=readline.createInterface({
    input: process.stdin,
    output: process.stdout
    });
var questionArray=["Press 1:Display devices based on Touch\nPress 2:Display devices by Screen Size\n","Press 1:Touch screen\nPress 2:Non-Touch screen \n","Enter the size\n",
"Press 1:Display devices only by touch\nPress 2:Filter by Screen Size\n",
"Press 1:Display devices only by screen size\nPress 2:Filter by touch\n"];

var _touch,_size;
var touchIfSelected=false;

let displayAgentExport=require("./displayDevices");

async function beginQuestion(){

    console.log("\n-----------------------")
    var answer1=await askQuestion(questionArray[0]);
    console.log("\n-----------------------")
    if(parseInt(answer1)==0)
    {
        console.log("Thank you");
        process.exit();
    }
    if(parseInt(answer1)==1)
        touchSelected();
    if(parseInt(answer1)==2)
    sizeSelected();
    if(answer1!=1 && answer1!=2)
    {
        console.log("You have entered wrong input");
        beginQuestion();
    }
}
async function touchSelected(){
    touchIfSelected=true;
    var answer2=await askQuestion(questionArray[1]);
    if(parseInt(answer2)==0)
    {
        console.log("Thank you");
        process.exit();
    }
    else{
    console.log("\n-----------------------")
   _touch=touch(answer2);

   // console.log(`Touch ${_touch}`)

    var answer3=await askQuestion(questionArray[3]);
    if(parseInt(answer3)==0)
    {
        console.log("Thank you");
        process.exit();
    }
    else{
    console.log("\n-----------------------")
    if(parseInt(answer3)==1)
    {
        displayAgentExport.displayTouch(global.Philips.HealthCare.MonitoringDevices,_touch);
    }
    if(parseInt(answer3)==2)
    {
        var answer4=await askQuestion(questionArray[2]);
        if(parseInt(answer4)==0)
        {
            console.log("Thank you");
              process.exit();
        }
        else{
        console.log("\n-----------------------")
        _size=answer4;
        displayAgentExport.display(global.Philips.HealthCare.MonitoringDevices,_touch,_size);
        }
    } 
} 
}  
}

async function sizeSelected(){
    
    var answer2=await askQuestion(questionArray[2]);
    if(parseInt(answer2)==0)
    {
        console.log("Thank you");
              process.exit();
    }
    else{
    console.log("\n-----------------------")
    _size=answer2;

    if(touchIfSelected==true){
        displayAgentExport.display(global.Philips.HealthCare.MonitoringDevices,_touch,_size);
    }
    else{
        var answer3=await askQuestion(questionArray[4]);
        if(parseInt(answer3)==0)
        {
            console.log("Thank you");
              process.exit();
        }
        else
        {
        console.log("\n-----------------------")
        if(answer3==1){
            displayAgentExport.displaySize(global.Philips.HealthCare.MonitoringDevices,_size);
        }
        else if(answer3==2){
            var answer4=await askQuestion(questionArray[1]);
            if(parseInt(answer4)==0)
            {
                console.log("Thank you");
              process.exit();
            }
            else{
            console.log("\n-----------------------")
            _touch=touch(answer4);
            displayAgentExport.display(global.Philips.HealthCare.MonitoringDevices,_touch,_size);
            }
        }
    }
    }
}
}

function touch(answer)
{
  if(answer==1)
     return true;
  else 
    return false;  
}

 async function askQuestion( questions ) {
    return new Promise(( res ) => {
        r1.question(questions, answer => {
          res(answer);
        })
 })
}
module.exports={questioning:beginQuestion};



//module.exports={questioning:beginQuestion};
