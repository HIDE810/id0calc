<?php

$fp = fopen('movable.sed', 'r');
fseek($fp, 0x110);
$content = fread($fp, 0x10);
fclose($fp);
$keyY = str_split(strtoupper(hash('sha256', $content)), 2);

$id0 = array();

for($i=0; $i<4; $i++) {
    array_push($id0, join('', array_reverse(array_slice($keyY, $i*4, 4))));
}

echo join('', $id0);