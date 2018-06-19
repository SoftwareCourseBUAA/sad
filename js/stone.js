// JavaScript Document
stone.js 
//**************************************神吹表格操作函数******************************************************* 
//隐藏列 
function setHiddenRow(tb,iCol){ 
for (i=0;i<oTable.rows.length;i++){ 
tb.rows[i].cells[iCol].style.display = oTable.rows[i].cells[iCol].style.display=="none"?"block":"none"; 
} 
} 
//隐藏行 
function setHiddenRow(tb,iRow){ 
tb.rows[iRow].style.display = oTable.rows[iRow].style.display == "none"?"block":"none"; 
} 
//创建表格 
function createTable(id,rows,cells,tbid){ 
var tb=document.createElement("table"); 
var tbody = document.createElement("tbody"); 
for(var i=0;i<rows;i++){ 
var tr=document.createElement("tr"); 
for(var j=0;j<cells;j++){ 
var cell=document.createElement("td"); 
tr.appendChild(cell); 
} 
tbody.appendChild(tr); 
} 
tb.appendChild(tbody); 
tb.setAttribute("id",tbid);//设置创建的TABLE的ID 
document.getElementById(id).appendChild(tb); 
} 
//插入文本 
function insertText(tb,row,cel,text){ 
txt=document.createTextNode(text); 
tb.rows[row].cells[cel].appendChild(txt); 
} 
//修改文本 
function updateText(tb,row,cel,text){ 
tb.rows[row].cells[cel].firstChild.nodeValue=text; 
} 
//添加子节点 
function toAppendChild(tb,row,cel,child){ 
tb.rows[row].cells[cel].appendChild(child); 
} 
//删除某行 
function removeRow(tb,row){ 
tb.lastChild.removeChild(tb.rows[row]); 
} 
//单元格设置属性 
function cellSetAttribute(tb,row,col,attributeName,attributeValue){ 
tb.rows[row].cells[col].setAttribute(attributeName,attributeValue); 
} 
//单元格添加监听器 
function cellAddListener(tb,row,cel,event,fun){ 
if(window.addEventListener) 
{ 
//其它浏览器的事件代码: Mozilla, Netscape, Firefox 
//添加的事件的顺序即执行顺序 //注意用 addEventListener 添加带on的事件，不用加on 
// img.addEventListener('click', delRow(this), true); 
tb.rows[row].cells[cel].addEventListener(event,fun, true); 
} 
else 
{ 
//IE 的事件代码 在原先事件上添加 add 方法 
// img.attachEvent('onclick',delRow(this)); 
tb.rows[row].cells[cel].attachEvent("on"+event,fun); 
} 
} 
//新增行 
function insertRow(oTable){ 
var tr=document.createElement("tr"); 
for (i=0;i<oTable.rows[0].cells.length;i++){ 
var td= document.createElement("td"); 
tr.appendChild(td); 
} 
oTable.lastChild.appendChild(tr); 
} 
//行设置属性 
function rowSetAttribute(tb,row,attributeName,attributeValue){ 
tb.rows[row].setAttribute(attributeName,attributeValue); 
} 
//行添加监听器 
function rowAddListener(tb,row,event,fun){ 
if(window.addEventListener) 
{ 
//其它浏览器的事件代码: Mozilla, Netscape, Firefox 
//添加的事件的顺序即执行顺序 //注意用 addEventListener 添加带on的事件，不用加on 
// img.addEventListener('click', delRow(this), true); 
tb.rows[row].addEventListener(event,fun, true); 
} 
else 
{ 
//IE 的事件代码 在原先事件上添加 add 方法 
// img.attachEvent('onclick',delRow(this)); 
tb.rows[row].attachEvent("on"+event,fun); 
} 
} 
//新增列 
function addCells(tb){ 
for (i=0;i<tb.rows.length;i++){ 
var td= document.createElement("td"); 
tb.rows[i].appendChild(td); 
} 
} 
//批量修改单元格属性 
function cellsSetAttribute(oTable,attributeName,attributeValue){ 
for (i=0;i<oTable.rows.length;i++){ 
for (j=0;j<oTable.rows[i].cells.length;j++){ 
oTable.rows[i].cells[j].setAttribute(attributeName,attributeValue); 
} 
} 
} 
//合并只支持单向合并 
//行合并 
function mergerRow(tb,row,cell,num){ 
for(var i= (row+1),j=0;j<(num-1);j++){ 
tb.rows[i].removeChild(tb.rows[i].cells[cell]); 
} 
tb.rows[row].cells[cell].setAttribute("rowspan",num); 
// document.getElementById('c').innerHTML=document.getElementById('u').innerHTML; 
} 
//列合并 
function mergerCell(tb,row,cell,num){ 
for(var i= (cell+1), j=0;j<(num-1);j++){ 
tb.rows[row].removeChild(tb.rows[row].cells[i]); 
} 
tb.rows[row].cells[cell].setAttribute("colspan",num); 
// document.getElementById('c').innerHTML=document.getElementById('u').innerHTML; 
} 