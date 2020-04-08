"use strict";

var canvas;
var gl;

var NumVertices = 36;

var pointsArray = [];
var colorsArray = [];

var xAxis = 0;
var yAxis = 1;
var zAxis = 2;

var axis = 0;

var thetav = [0, 0, 0];
// 3 angles for rotate around x, y, and z axises.
var thetavLoc;
// use to bind with thetav in vertex shader 
var flag = false;
// to indicate if the pyramid needs to rotate


//Cube 
// var vertices = [
//     vec4(-0.5, -0.5, 1.5, 1.0),
//     vec4(-0.5, 0.5, 1.5, 1.0),
//     vec4(0.5, 0.5, 1.5, 1.0),
//     vec4(0.5, -0.5, 1.5, 1.0),
//     vec4(-0.5, -0.5, 0.5, 1.0),
//     vec4(-0.5, 0.5, 0.5, 1.0),
//     vec4(0.5, 0.5, 0.5, 1.0),
//     vec4(0.5, -0.5, 0.5, 1.0)
// ];

// var vertexColors = [
//     vec4(0.0, 0.0, 0.0, 1.0),  // black
//     vec4(1.0, 0.0, 0.0, 1.0),  // red
//     vec4(1.0, 1.0, 0.0, 1.0),  // yellow
//     vec4(0.0, 1.0, 0.0, 1.0),  // green
//     vec4(0.0, 0.0, 1.0, 1.0),  // blue
//     vec4(1.0, 0.0, 1.0, 1.0),  // magenta
//     vec4(0.0, 1.0, 1.0, 1.0),  // cyan
//     vec4(1.0, 1.0, 1.0, 1.0),  // white
// ];

//Pyramid 
var vertices = [
    vec4(0.5, -0.2722, 1.2886, 1), //A 0
    vec4(0.0, -0.2722, 1 - 0.5773, 1), //B 1 
    vec4(-0.5, -0.2722, 1.2886, 1), //C 2 
    vec4(0.0, 0.5443, 1.0, 1) //D 3
];

var vertexColors = [
    vec4(0.0, 0.0, 0.0, 1.0),  // black 0
    vec4(1.0, 0.0, 0.0, 1.0),  // red 1
    vec4(0.0, 1.0, 0.0, 1.0), // green 2
    vec4(0.0, 0.0, 1.0, 1.0)  // blue 3
];


var near = 0.3;
var far = 3.0;
var radius = 4.0;
var theta = 0.0;
var phi = 0.0;
var dr = 5.0 * Math.PI / 180.0;

var fovy = 45.0;  // Field-of-view in Y direction angle (in degrees)
var aspect = 1.0;       // Viewport aspect ratio

var modelViewMatrix, projectionMatrix;
var modelViewMatrixLoc, projectionMatrixLoc;
var eye;
const at = vec3(0.0, 0.0, 0.0);
const up = vec3(0.0, 1.0, 0.0);


// //Cube 
// function quad(a, b, c, d) {
//     pointsArray.push(vertices[a]);
//     colorsArray.push(vertexColors[a]);
//     pointsArray.push(vertices[b]);
//     colorsArray.push(vertexColors[a]);
//     pointsArray.push(vertices[c]);
//     colorsArray.push(vertexColors[a]);
//     pointsArray.push(vertices[a]);
//     colorsArray.push(vertexColors[a]);
//     pointsArray.push(vertices[c]);
//     colorsArray.push(vertexColors[a]);
//     pointsArray.push(vertices[d]);
//     colorsArray.push(vertexColors[a]);
// }

// function colorCube() {
//     quad(1, 0, 3, 2);
//     quad(2, 3, 7, 6);
//     quad(3, 0, 4, 7);
//     quad(6, 5, 1, 2);
//     quad(4, 5, 6, 7);
//     quad(5, 4, 0, 1);
// }

//Pyramid 
function triple(a, b, c, d) {
    //push triangle vertices into buffer 
    pointsArray.push(vertices[a]);
    colorsArray.push(vertexColors[a]);
    pointsArray.push(vertices[b]);
    colorsArray.push(vertexColors[a]);
    pointsArray.push(vertices[c]);
    colorsArray.push(vertexColors[a]);
}

function colorPyramid() {
    triple(0, 2, 1);
    triple(1, 2, 3);
    triple(2, 3, 0);
    triple(3, 0, 1);
}

window.onload = function init() {
    canvas = document.getElementById("gl-canvas");

    gl = WebGLUtils.setupWebGL(canvas);
    if (!gl) { alert("WebGL isn't available"); }

    gl.viewport(0, 0, canvas.width, canvas.height);

    aspect = canvas.width / canvas.height;

    gl.clearColor(1.0, 1.0, 1.0, 1.0);

    gl.enable(gl.DEPTH_TEST);

    //
    //  Load shaders and initialize attribute buffers
    //
    var program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram(program);

    // colorCube();
    this.colorPyramid();

    var cBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, cBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(colorsArray), gl.STATIC_DRAW);

    var vColor = gl.getAttribLocation(program, "vColor");
    gl.vertexAttribPointer(vColor, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vColor);

    var vBuffer = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, vBuffer);
    gl.bufferData(gl.ARRAY_BUFFER, flatten(pointsArray), gl.STATIC_DRAW);

    var vPosition = gl.getAttribLocation(program, "vPosition");
    gl.vertexAttribPointer(vPosition, 4, gl.FLOAT, false, 0, 0);
    gl.enableVertexAttribArray(vPosition);

    // Bind with variables in shader
    modelViewMatrixLoc = gl.getUniformLocation(program, "modelViewMatrix");
    projectionMatrixLoc = gl.getUniformLocation(program, "projectionMatrix");
    //Bind with thetav in shader
    thetavLoc = gl.getUniformLocation(program, "thetav");


    // sliders for viewing parameters

    document.getElementById("zFarSlider").onchange = function (event) {
        far = event.target.value;
    };
    document.getElementById("zNearSlider").onchange = function (event) {
        near = event.target.value;
    };
    document.getElementById("radiusSlider").onchange = function (event) {
        radius = event.target.value;
    };
    document.getElementById("thetaSlider").onchange = function (event) {
        theta = event.target.value * Math.PI / 180.0;
    };
    document.getElementById("phiSlider").onchange = function (event) {
        phi = event.target.value * Math.PI / 180.0;
    };
    document.getElementById("aspectSlider").onchange = function (event) {
        aspect = event.target.value;
    };
    document.getElementById("fovSlider").onchange = function (event) {
        fovy = event.target.value;
    };

    document.getElementById("xButton").onclick = function () {
        axis = xAxis;
    };
    document.getElementById("yButton").onclick = function () {
        axis = yAxis;
    };
    document.getElementById("zButton").onclick = function () {
        axis = zAxis;
    };
    document.getElementById("ButtonT").onclick = function () {
        flag = !flag;
    };

    render();
}


var render = function () {

    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    eye = vec3(radius * Math.sin(theta) * Math.cos(phi),
        radius * Math.sin(theta) * Math.sin(phi), radius * Math.cos(theta));

    // Calculate the values 
    modelViewMatrix = lookAt(eye, at, up);
    projectionMatrix = perspective(fovy, aspect, near, far);

    if (flag) thetav[axis] += 2.0;

    // Pass back to shader 
    gl.uniformMatrix4fv(modelViewMatrixLoc, false, flatten(modelViewMatrix));
    gl.uniformMatrix4fv(projectionMatrixLoc, false, flatten(projectionMatrix));
    //Pass thetav value back to shader
    gl.uniform3fv(thetavLoc, thetav);

    gl.drawArrays(gl.TRIANGLES, 0, NumVertices);
    requestAnimFrame(render);
}
