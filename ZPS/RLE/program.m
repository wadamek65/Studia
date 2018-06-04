close all;
clear all;
im = imread('losowy.gif');
it = length(im) * length(im);
i = 1;
i = uint64(i)
out = []
tic
while i< it
   bit = im(i);
   i = i + 1;
   tmp = 0;
   while im(i) == bit && i< it
       tmp = tmp + 1;
       i = i + 1;
   end
   if mod(i, 10000) == 0
       i
   end
   str = dec2base(tmp, 256);
   out = [out zeros(length(str)) str];
end
toc