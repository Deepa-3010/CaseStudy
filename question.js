const readline = require('readline');
const r1=readline.createInterface({
    input: process.stdin,
    output: process.stdout
    });
// const r2=readline.createInterface({
//         input: process.stdin,
//         output: process.stdout
//         });
var _touch;
var _size;
let displayAgentExport=require("./displayDevices");

r1.question('Type 1 for touchscreen and 2 for non touch screen: ', (touch) => {
        if(touch==1)
            _touch=true;
        if(touch==2)
            _touch=false;  
        if(touch!=1 && touch!=2)
        {
            console.log("You have entered other than 1 or 2 plz try again");
            r1.close();
        }     
        if(touch==1 || touch==2)
        {
            r1.question('Enter the screensize required ',(screenSize )=> {
                _size=screenSize;
                (displayAgentExport.display(global.Philips.HealthCare,_touch,_size));
                r1.close();
            
            });
    }});

module.exports={_touch,_size};