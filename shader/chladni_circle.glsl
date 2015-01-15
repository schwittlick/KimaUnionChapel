#version 120

#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_COLOR_SHADER
#define M_PI 3.1415926535897932384626433832795

uniform sampler2D texture;
uniform vec2 texOffset;

uniform vec2 resolution;
uniform float m;
uniform float n;
uniform float time;
uniform float scale;
uniform int poles;

float ripple(float dist, float shift)
{
	return cos(64.0 * dist + shift) / (1.0 + 1.0 * dist);
}

float rand(vec2 co){
    return fract(sin(dot(co.xy ,vec2(12.9898,78.233))) * 43758.5453);
}

void main(void) {

  float larger = max(resolution.x, resolution.y) / scale;
  	vec2 uv = (gl_FragCoord.xy - .5*resolution.xy) / larger;
  	vec2 uvflip = vec2(uv.x, -uv.y);
  	vec2 cursor = (vec2( m, n ) - .5*resolution.xy) / larger;
  	vec2 blessr = vec2(-cursor.x, cursor.y);
  	vec2 position = ( gl_FragCoord.xy / resolution.xy );

  	float lum = .5 + 0.1 * ripple(length(uv), 0.0) + 0.0;

  	float twopi = 2.0 * M_PI;
  	int count = poles;
  	float fcount = float(count);
  	vec2 rot = vec2(cos(twopi*.618), sin(twopi*.618));
  	vec2 tor = vec2(-sin(twopi*.618), cos(twopi*.618));
  	for (int i = 0; i < count; ++i)
  	{
  		lum += .2 * ripple(length(cursor - uv), -time);
  		cursor = cursor.x*rot + cursor.y*tor;
  	}

  	lum = 3.0*lum*lum - 2.0*lum*lum*lum;

    gl_FragColor = vec4(lum, lum, lum, 1.0);
}
