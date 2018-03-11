clear all;
close all;
M = 60;
n = 3;
pi = 3.14;
theta = 0:0.01:2*pi;
obrazy = []

for m = 0 : 1 : 128;
    rho = sin(3*theta + m*pi/80);
    polar(theta,rho, 'b');
    lel = getframe(gcf, [100, m*2.5 + 60 , 370, 2.5]);
    obrazy = [lel.cdata; obrazy];
end
figure(2)
image(obrazy)
