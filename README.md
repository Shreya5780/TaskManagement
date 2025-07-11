## Request Lifecycle (How Everything Works Together)

    ✅ User sends POST /login with credentials → hits UserController, which calls UserService

    ✅ Using UserRepo Check if username and password exist or not, if not UsernameNotFoundException

    ✅ Credentials checked using AuthenticationManager (where it create UsernamePasswordAuthenticationToken) (AuthenticationManager uses AuthenticationProvider(here DaoAuthenticationProvider - which receive the UsernamePasswordAuthenticationToken, load user from UserDeatilService, validate password))  
    
    ✅ If correct, jWTService generates a JWT token → response sent to client

    ✅ For all other routes, client sends JWT in Authorization: Bearer <token>

    ✅ JwtFilter intercepts request, extracts & validates token(custome security filter, runs before every request and extract JWT form header, validate token, sets user as authenticated)

    ✅ If token is valid, Spring sets authentication in the context

    ✅ Protected route controller runs successfully


## 🟡 Why Do We Use JWT?

We use JWT (JSON Web Token) to:

✅ Log in once and
✅ Stay logged in without checking username/password every time.

It’s used to secure APIs — especially in mobile apps, web apps, and REST APIs.
🏠 Simple Real-Life Example

Imagine you go to a cinema:

    🎟️ At the entry, you show your ID and buy a ticket.

    🪪 The ticket now says: Your name, seat number, time, etc.

    🎬 Now for 3 hours, whenever someone checks your ticket — you don’t need to show ID again.

🔄 That ticket = JWT

    It proves who you are

    It’s issued once

    You carry it with every request (like an API call)


We use JWT to:

    Avoid logging in again and again

    Prove who the user is

    Keep APIs secure and fast (stateless — no session saved)
