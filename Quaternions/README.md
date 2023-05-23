# Quaternion Graphics

*I have preserved this high school project as-is, only recently adding this readme.*

---

This project explores the use of quaternions for rotating and rendering three-dimensional objects.

![Quaternion Cubes](../media/quaternions.gif)

## Overview of Quaternions

Quaternions are the three-dimensional extension of complex numbers. Complex numbers, represented as $a + bi$, are
a natural way to store rotations in two dimensions. To rotate a point $p$ counter-clockwise by an angle $\theta$,
just multiply $z p$ where $z$ is the complex number $\cos{\theta} + i \sin{\theta}$.

With quaternions, instead of only $i$ (where $i * i = -1$), there are three numbers $i$, $j$, and $k$, whose
multiplication table is shown below.

|     |   1   |   i   |   j   |   k   |
|:---:|:-----:|:-----:|:-----:|:-----:|
|**1**|  $1$  |  $i$  |  $j$  |  $k$  |
|**i**|  $i$  | $-1$  |  $k$  | $-j$  |
|**j**|  $j$  | $-k$  | $-1$  |  $i$  |
|**k**|  $k$  |  $j$  | $-i$  | $-1$  |

To rotate a point $p$ in 3D around an axis defined by the unit vector $u = (u_x, u_y, u_z)$ by an angle $\theta$,
one evaluates the product $q p q^{-1}$, where $q$ is the quaternion
$\cos{\frac{\theta}{2}} + (u_x i + u_y j + u_z k) \sin{\frac{\theta}{2}}$.

## The Cube World

In this project, each cube consists of a collection of points. Using the WASD keys, spacebar, and mouse, the player
can control the location and orientation of the camera. To render the cubes, the coordinates of the cube points
are transformed to the camera's current reference frame (using quaternions for the rotation) and drawn on the screen.
