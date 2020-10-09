var input_file = document.getElementById("file");

function toHexString(byteArray) {
    return Array.from(byteArray, function(byte) {
    return ('0' + (byte & 0xFF).toString(16)).slice(-2);
    }).join('')
}

input_file.onchange = function() {
    
    var file = input_file.files[0];
    if(!file) return;
    
    var file_reader = new FileReader();
    
    file_reader.onload = function() {
        
        var ary_u8 = new Uint8Array(file_reader.result);
        
        const shaObj = new jsSHA("SHA-256", "HEX");
        shaObj.update(toHexString(ary_u8.slice(272, 288)));
        const hash = shaObj.getHash("HEX").match(/.{2}/g);
        
        var id0 = [];
        
        for(let i=0; i<=12; i+=4) {
            id0.push(hash.slice(i, i+4).reverse().join(''));
        }
        
        document.getElementById("id0").innerHTML = id0.join('').toUpperCase();
    };
    
    file_reader.readAsArrayBuffer(file);
}