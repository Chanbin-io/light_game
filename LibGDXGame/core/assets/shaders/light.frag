// light.frag
#ifdef GL_ES
 precision mediump float;
#endif

varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform vec2 u_center;    // 빛의 중심 (0~1)
uniform float u_radius;   // 빛 반경 (0~1)
uniform float u_intensity; // 강도

void main() {
    vec4 color = texture2D(u_texture, v_texCoords);
    // 화면 좌표를 0~1 로 normalize
    vec2 uv = gl_FragCoord.xy / resolution.xy;
    float d = distance(uv, u_center);
    float falloff = clamp(1.0 - (d / u_radius), 0.0, 1.0);
    gl_FragColor = vec4(color.rgb * falloff * u_intensity, color.a);
}
