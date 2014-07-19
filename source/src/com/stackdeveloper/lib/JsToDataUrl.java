/*
 * https://code.google.com/p/todataurl-png-js/
 * 
 * 
 */
package com.stackdeveloper.lib;

public class JsToDataUrl 
{
	  public static String buidScript()
	   {
		 StringBuilder sb = new StringBuilder();
		 sb.append("Number.prototype.toUInt=function(){ return this<0?this+4294967296:this; }; ");
		 sb.append("Number.prototype.bytes32=function(){ return [(this>>>24)&0xff,(this>>>16)&0xff,(this>>>8)&0xff,this&0xff]; }; ");
		 sb.append("Number.prototype.bytes16sw=function(){ return [this&0xff,(this>>>8)&0xff]; }; ");

		 sb.append("Array.prototype.adler32=function(start,len){ ");
		 sb.append("	switch(arguments.length){ case 0:start=0; case 1:len=this.length-start; } ");
		 sb.append("	var a=1,b=0; ");
		 sb.append("	for(var i=0;i<len;i++){ ");
		 sb.append("		a = (a+this[start+i])%65521; b = (b+a)%65521; ");
		 sb.append("	} ");
		 sb.append("	return ((b << 16) | a).toUInt(); ");
		 sb.append("};");

		 sb.append("Array.prototype.crc32=function(start,len){ ");
		 sb.append("	switch(arguments.length){ case 0:start=0; case 1:len=this.length-start; } ");
		 sb.append("	var table=arguments.callee.crctable; ");
		 sb.append("	if(!table){ ");
		 sb.append("		table=[]; ");
		 sb.append("		var c; ");
		 sb.append("		for (var n = 0; n < 256; n++) { ");
		 sb.append("			c = n; ");
		 sb.append("			for (var k = 0; k < 8; k++) ");
		 sb.append("				c = c & 1?0xedb88320 ^ (c >>> 1):c >>> 1; ");
		 sb.append("			table[n] = c.toUInt(); ");
		 sb.append("		} ");
		 sb.append("		arguments.callee.crctable=table; ");
		 sb.append("	} ");
		 sb.append("	var c = 0xffffffff; ");
		 sb.append("	for (var i = 0; i < len; i++) ");
		 sb.append("		c = table[(c ^ this[start+i]) & 0xff] ^ (c>>>8); ");
		 sb.append("	return (c^0xffffffff).toUInt(); ");
		 sb.append("}; ");


		 sb.append("(function(){ ");
		 sb.append("	var toDataURL=function(){ ");
		 sb.append("		var imageData=Array.prototype.slice.call(this.getContext(\"2d\").getImageData(0,0,this.width,this.height).data); ");
		 sb.append("		var w=this.width; ");
		 sb.append("		var h=this.height; ");
		 sb.append("		var stream=[ ");
		 sb.append("			0x89,0x50,0x4e,0x47,0x0d,0x0a,0x1a,0x0a, ");
		 sb.append("			0x00,0x00,0x00,0x0d,0x49,0x48,0x44,0x52 ");
		 sb.append("		]; ");
		 sb.append("		Array.prototype.push.apply(stream, w.bytes32() ); ");
		 sb.append("		Array.prototype.push.apply(stream, h.bytes32() ); ");
		 sb.append("		stream.push(0x08,0x06,0x00,0x00,0x00); ");
		 sb.append("		Array.prototype.push.apply(stream, stream.crc32(12,17).bytes32() ); ");
		 sb.append("		var len=h*(w*4+1); ");
		 sb.append("		for(var y=0;y<h;y++) ");
		 sb.append("			imageData.splice(y*(w*4+1),0,0); ");
		 sb.append("		var blocks=Math.ceil(len/32768);");
		 sb.append("		Array.prototype.push.apply(stream, (len+5*blocks+6).bytes32() );");
		 sb.append("		var crcStart=stream.length; ");
		 sb.append("		var crcLen=(len+5*blocks+6+4); ");
		 sb.append("		stream.push(0x49,0x44,0x41,0x54,0x78,0x01); ");
		 sb.append("		for(var i=0;i<blocks;i++){ ");
		 sb.append("			var blockLen=Math.min(32768,len-(i*32768)); ");
		 sb.append("			stream.push(i==(blocks-1)?0x01:0x00); ");
		 sb.append("			Array.prototype.push.apply(stream, blockLen.bytes16sw() ); ");
		 sb.append("			Array.prototype.push.apply(stream, (~blockLen).bytes16sw() ); ");
		 sb.append("			var id=imageData.slice(i*32768,i*32768+blockLen); ");
		 sb.append("			Array.prototype.push.apply(stream, id ); ");
		 sb.append("		} ");
		 sb.append("		Array.prototype.push.apply(stream, imageData.adler32().bytes32() ); ");
		 sb.append("		Array.prototype.push.apply(stream, stream.crc32(crcStart, crcLen).bytes32() ); ");

		 sb.append("		stream.push(0x00,0x00,0x00,0x00,0x49,0x45,0x4e,0x44); ");
		 sb.append("		Array.prototype.push.apply(stream, stream.crc32(stream.length-4, 4).bytes32() ); ");
		 sb.append("		return \"data:image/png;base64,\"+btoa(stream.map(function(c){ return String.fromCharCode(c); }).join('')); ");
		 sb.append("	}; ");
		 	
		 sb.append("	var tdu=HTMLCanvasElement.prototype.toDataURL; ");
		 	
		 sb.append("	HTMLCanvasElement.prototype.toDataURL=function(type){ ");

		 sb.append("			var res=tdu.apply(this,arguments); ");
		 sb.append("			if(res == \"data:,\"){ ");
		 sb.append("				HTMLCanvasElement.prototype.toDataURL=toDataURL; ");
		 sb.append("				return this.toDataURL(); ");
		 sb.append("			}else{ ");
		 sb.append("				HTMLCanvasElement.prototype.toDataURL=tdu; ");
		 sb.append("				return res; ");
		 sb.append("			} ");
		 sb.append("	} ");

		 sb.append("})(); ");
		 return sb.toString();
	   }
}
