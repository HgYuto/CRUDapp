//必須入力チェック
function mustCheck(name,id){
    if(id.value == "" ){
        return name + "を入力して下さい。\r\n";
    }
    else{
        return" ";
    }
};

//文字列の長さチェック
function lengthCheck(name,id,limitNo){
	if(id.value.length > limitNo){
        return name + "に" + limitNo + "文字以内で入力してください。\r\n";
    }
    else{
        return" ";
    }
};

//サロゲート文字
function surroCheck(name,id){
	for(i = 0; id.value.length > i ; i++){
   		if ( 0xD800 <= id.value.charCodeAt(i) && id.value.charCodeAt(i) <= 0xDBFF) {

			if(0xDC00 <= id.value.charCodeAt(i+1) && id.value.charCodeAt(i+1) <= 0xDFFF){
				return "サロゲート文字は" + name + "に入力できません。\r\n";
			}
			else{
				return " ";
			}
		}
		else{
		return " ";
		}
	}
};

//禁止文字チェック
function vanWordCheck(name,id){
   	if (id.value.match(/["'\*\(\)%\[\]^<>,]+/)) {
		return "\"'\*\(\)%\[\]^<>,は" + name + "に入力できません。\r\n";
	}
	else{
		return" ";
	}
};


//指定文字列 半角数字のみ
function isNumHan(name,id){
    if(id.value.match(/[^0-9]+/)){
    	return name + "に半角数字を入力して下さい。\r\n";
    }
     else {
        return" ";
    }
};

//指定文字列 英数字のみ
function isAlpNumHan(name,id){
    if(id.value.match(/[^-A-Za-z0-9]+/)){
        return name + "に半角英数字または、「-」を入力して下さい。\r\n";
    }
     else {
        return" ";
    }
};

//指定文字列 メールアドレス用
function isMailHan(name,id){
    if(id.value.match(/^(\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/)){
    	return" ";
    }
     else {
        return name + "に入力出来るのは、半角英数字,「-」,「+」,「.」,「@」です。\r\n";
    }
};

//指定文字列 正数のみ
function isPoNum(name,id){
    if(Math.sign(id.value) > 0){
        return " ";
    }
     else {
        return name + "に正数を入力して下さい。\r\n";
    }
};

//test用(白髭)
function OutputTest(id){
        return "テスト\r\n";
};
