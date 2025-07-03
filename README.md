Request Lifecycle (How Everything Works Together)

    ✅ User sends POST /login with credentials → hits AuthController

    ✅ Credentials checked using AuthenticationManager, which calls MyUserDetailsService

    ✅ If correct, JwtUtil generates a JWT token → response sent to client

    ✅ For all other routes, client sends JWT in Authorization: Bearer <token>

    ✅ JwtRequestFilter intercepts request, extracts & validates token

    ✅ If token is valid, Spring sets authentication in the context

    ✅ Protected route controller runs successfully

🟡 Why Do We Use JWT?

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
