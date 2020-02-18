"use strict";

var gl;

var theta = 0.0;
var thetaLoc;

var color;
var colorLoc;

var speed = 100;
var direction = true;

window.onload = function init() {
  var canvas = document.getElementById("gl-canvas");

  gl = WebGLUtils.setupWebGL(canvas);
  if (!gl) { alert("WebGL isn't available"); }

  //
  //  Configure WebGL
  //
  gl.viewport(0, 0, canvas.width, canvas.height);
  gl.clearColor(1.0, 1.0, 1.0, 1.0); //white:background color

  //  Load shaders and initialize attribute buffers

  var program = initShaders(gl, "vertex-shader", "fragment-shader");
  gl.useProgram(program);

  //below is equilater triangle coordinates that its center is at the origin (0, 0)
  var vertices = [
    vec2(Math.cos(Math.PI / 3), Math.sin(Math.PI / 3)),
    vec2(Math.cos(Math.PI), Math.sin(Math.PI)),
    vec2(Math.cos(5 * Math.PI / 3), Math.sin(5 * Math.PI / 3))
  ];

  var triangle_colors = [
    vec4(1.0, 0.0, 0.0, 1.0),//red
    vec4(0.0, 1.0, 0.0, 1.0), //green 
    vec4(0.0, 0.0, 1.0, 1.0), //blue
    vec4(0.0, 0.0, 0.0, 1.0) //black 
  ];

  // Load the data into the GPU

  var bufferId = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
  gl.bufferData(gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW);

  // Associate out shader variables with our data buffer

  var vPosition = gl.getAttribLocation(program, "vPosition");
  gl.vertexAttribPointer(vPosition, 2, gl.FLOAT, false, 0, 0);
  gl.enableVertexAttribArray(vPosition);

  thetaLoc = gl.getUniformLocation(program, "theta");

  color = triangle_colors[0]; //default color: red 
  colorLoc = gl.getUniformLocation(program, "color");

  // Initialize event handlers

  document.getElementById("slider").onchange = function (event) {
    speed = 100 - event.target.value;
  };
  document.getElementById("Direction").onclick = function (event) {
    direction = !direction;
  };

  document.getElementById("Controls").onclick = function (event) {
    switch (event.target.index) {
      case 0:
        direction = !direction;
        break;

      case 1:
        speed /= 2.0;
        break;

      case 2:
        speed *= 2.0;
        break;

      case 3:
        //set triangle to red
        color = triangle_colors[0];
        break;

      case 4:
        //set triangle to green 
        color = triangle_colors[1];
        break;

      case 5:
        //set triangle to blue
        color = triangle_colors[2];
        break;

      case 6:
        //set triangle to black 
        color = triangle_colors[3];
        break;


    }
  };

  window.onkeydown = function (event) {
    var key = String.fromCharCode(event.keyCode);
    switch (key) {
      case '1':
        direction = !direction;
        break;

      case '2':
        speed /= 2.0;
        break;

      case '3':
        speed *= 2.0;
        break;
    }
  };


  render();
};

function render() {
  gl.clear(gl.COLOR_BUFFER_BIT);

  //Enable the rotation
  theta += (direction ? 0.1 : -0.1);
  gl.uniform1f(thetaLoc, theta);
  gl.uniform4fv(colorLoc, color); //pass the uniform color to frag. shader to apply the color

  gl.drawArrays(gl.TRIANGLES, 0, 3); //for triangle 

  //Enable the rotation
  setTimeout(
    function () { requestAnimFrame(render); },
    speed
  );

}
