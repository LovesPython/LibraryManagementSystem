const button = document.getElementsByClassName("delete");
const form = document.querySelector("#form");
var res = "本当に削除してもよろしいですか？";

function Check(){
  if (window.confirm(res)==true) {
    console.log("1");
    return true;
  }else{
    console.log("2");
    return false;
  }
}
