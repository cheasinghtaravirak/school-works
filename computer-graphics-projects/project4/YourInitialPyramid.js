"use strict";

var canvas;
var gl;

var NumVertices = 12; //For pyramid: 3 * 4 = 12 vertices

var points = [];
var colors = [];

var xAxis = 0;
var yAxis = 1;
var zAxis = 2;

var axis = 0;
var theta = [0, 0, 0];

var thetaLoc;

window.onload = function init() {
    canvas = document.getElementById("gl-canvas");

    gl = WebGLUtils.setupWebGL(canvas);
    if (!gl) { alert("WebGL isn't available"); }

    // colorCube();
    this.colorPyramid();

    gl.viewport(0, 0, canvas.width, canvas.height);
    gl.clearColor(1.0, 1.0, 1.0, 1.0);

    gl.enable(gl.DEPTH_TEST);

    //
    //  Load shaders and initialize attribute buffers
    //
    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    var cBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);

    var vColor = gl.getAttribLocation(program, "vColor");
    gl.vertexAttribPointer(vColor, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vColor);

    var vBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(points), gl.STATIC_DRAW);


    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vPosition);

    thetaLoc = gl.getUniformLocation(program, "theta");

    //event listeners for buttons

    document.getElementById("xButton").onclick = function () {
        axis = xAxis;
    };
    document.getElementById("yButton").onclick = function () {
        axis = yAxis;
    };
    document.getElementById("zButton").onclick = function () {
        axis = zAxis;
    };

    render();
}

//4 faces of a pyramid
function colorPyramid() {
    triple(0, 1, 3); //face: ABD 
    triple(2, 0, 1); //face: CAB
    triple(3, 0, 2); //face: DAC 
    triple(1, 2, 3); //face: BCD
}

// // A function to create one pyramid face 
function triple(a, b, c) {
    var vertices = [
        vec4(0.5, -0.2722, 0.2886, 1), //A 0
        vec4(0.0, -0.2722, -0.5773, 1), //B 1 
        vec4(-0.5, -0.2722, 0.2886, 1), //C 2 
        vec4(0.0, 0.5443, 0.0, 1) //D 3
    ];

    var vertexColors = [
        [0.0, 0.0, 0.0, 1.0],  // black 0
        [1.0, 0.0, 0.0, 1.0],  // red 1
        [0.0, 1.0, 0.0, 1.0], // green 2
        [0.0, 0.0, 1.0, 1.0]  // blue 3
    ];

    //vertex color assigned by the index of the vertex
    var indices = [a, b, c]; //for triangle 

    for (var i = 0; i < indices.length; ++i) {
        points.push(vertices[indices[i]]);
        //colors.push( vertexColors[indices[i]] );
        // for solid colored faces use
        colors.push(vertexColors[a]);
    }
}


function render() {
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    theta[axis] += 2.0;
    gl.uniform3fv(thetaLoc, theta);

    gl.drawArrays(gl.TRIANGLES, 0, NumVertices);

    requestAnimFrame(render);
}
