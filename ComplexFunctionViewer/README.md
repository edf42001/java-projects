# Complex Function Viewer

This application visualizes complex functions in the 2D plane with color-coding. A complex number can be represented as a point on a plane,
and a complex function can be thought of as taking in these points and transforming them. We can then color code the original point
based on where the transformed point ended up. For example, this is the identity function $f(z) = z$.

<img src="../media/complex_identity.png" width="500">

The closer the output value is to 0, the darker it is colored. Roots of the function are visible as the dark spots.
Here is the quadratic $f(z) = z^2 - 2$, with roots $-\sqrt{2}$ and $\sqrt{2}$.

<img src="../media/complex_quadtratic.png" width="500">

Here is the cubic $f(z) = z^3 + 1$. You can see the root at $-1$ and the other two equally spaced roots.

<img src="../media/complex_cubic.png" width="500">

And the quintic $f(z) = z^5 -3z^4 + z^3 - 2$

<img src="../media/complex_quintic.png" width="500">
