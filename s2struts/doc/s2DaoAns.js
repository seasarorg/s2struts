//  正解の配列(配列の順番は適当でOK)
    ans = new Array("Table3", "Column3", "ColumnNothing4", "N1Mapping2", "Bean1", 
                    "Args2", "ManyArgs2","Interceptor4", "DefDicon4", "SQLFileName2", "Relno2", 
                    "Relkeys4", "Query3", "QuerySQLComment3", "DefInsert1", "DefUpdate4",
                    "DefDelete2","SQLComment3", "SQLIf4", "SQLBegin3", "EntityManagerClass2",
                    "EntityManagerArgs3", "EntityManagerFind3", "EntityManagerFindArray1","EntityManagerFindBean4", "EntityManagerFindObject2",
                     "AutoDef3", "BatchUpdate1", "AutoSelect3", "AutoSelect2_4", "AutoSelect3_4");
                    
function check(linkName,linkNextName){
    count = 0;
    message = "";
    //x変数の初期化
    x = 0;
    for(i = 0; i<4; i++){
        if(document.myForm.elements["" + linkName][i].checked){
            if(answerCheck(document.myForm.elements["" + linkName][i].value)){
                message = "正解です!\n解説を表示しますか？";
                flag=confirm(message);
                if(flag){
                    location.href="s2DaoAns.html#" + linkName
                }
                else{
                    if(linkNextName != ""){
                        flag=confirm("次の問題に移りますか？");
                        if(flag){
                            location.href = "#" + linkNextName
                        }
                    }
                }
            }
            else{
                message = "不正解です。\n解説を表示しますか？";
                flag=confirm(message);
                if(flag){
                    location.href="s2DaoAns.html#" + linkName
                }
            }
            return true;
        }
        count = count + 1;
    }
    
    if(count == 4){
        alert("いずれかにチェックを入れてから「解答へGO」ボタンを押してください");  
    }
}

function answerCheck(answer){
     
    for(i = 0 ; i<ans.length; i++){
        if(ans[i] == answer){
            return true;
        }
    }
    return false;
}