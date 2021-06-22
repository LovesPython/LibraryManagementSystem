const button = document.getElementById("button");
let textbox;
let value;
let count;
let flag=1;

const form = document.querySelector("#form");
form.addEventListener("submit", function(){
  textbox = document.getElementById('text');
  value = textbox.value;
  console.log("value:"+value);
  console.log("kength:"+value.length);
  if(value){
    if (value.length<15) {
       flag=1;
     }
     if(value.length>15){
       flag=1;
     }
     if(value.length==15){
    	  flag=0;
    }
     if(flag==0){
       console.log("flag0"+flag);
       window.alert( '入力内容OK' );
       return true;
     }else{
       console.log("flag1"+flag);
       window.alert( '入力内容に不備があります。' ); // アラートを表示
       return false; // 送信中止
     }
  }
})
// function formCheck(value){
//   console.log("value:"+value);
//   console.log("kength:"+value.length);
//  if (value.length<15) {
//    flag=1;
//  }
//  if(value.length>15){
//    flag=1;
//  }
//  if(value.length==15){
// 	  flag=0;
// }
//  if(flag=0){
//    console.log("flag0"+flag);
//    return true;
//  }else{
//    console.log("flag1"+flag);
//    window.alert( '入力内容に不備があります。' ); // アラートを表示
//    return false; // 送信中止
//  }
// }
// textbox.addEventListener("keyup", () => {
//   console.log("kength"+value.length);
//  if (value.length<15) {
//    flag=1;
//  }
//  if(value.length>15){
//    flag=1;
//  }
//  if(value.length==15){
// 	  flag=0;
// }
//  if(flag=0){
//    console.log("flag0"+flag);
//    return true;
//  }else{
//    console.log("flag1"+flag);
//    window.alert( '入力内容に不備があります。' ); // アラートを表示
//    return false; // 送信中止
//  }
// });
// function inputChange(event){
//   console.log("kength"+value.length);
//   if(value.length==15){
//     console.log("15!");
//     button.disabled = false;
//   }else{
//     button.disabled = true;
//   }
// }
//
// text.addEventListener('input', inputChange);
