Request Lifecycle (How Everything Works Together)

    ✅ User sends POST /login with credentials → hits AuthController

    ✅ Credentials checked using AuthenticationManager, which calls MyUserDetailsService

    ✅ If correct, JwtUtil generates a JWT token → response sent to client

    ✅ For all other routes, client sends JWT in Authorization: Bearer <token>

    ✅ JwtRequestFilter intercepts request, extracts & validates token

    ✅ If token is valid, Spring sets authentication in the context

    ✅ Protected route controller runs successfully
