newman run Otus.postman_collection.json --folder "hw-06"

Otus

❏ hw-06
↳ registration user 1
POST http://arch.homework/sign-up [200 OK, 382B, 427ms]
✓  Response status is 200
┌
│ 'Request: ', { login: 'user-0', password: '123' }
│ 'Response Body: ', { id: 1, login: 'user-0', password: '123' }
└

↳ unautheticated get profile
GET http://arch.homework/profile [401 Unauthorized, 348B, 30ms]
✓  Response status is 401

↳ unautheticated update profile
PUT http://arch.homework/profile [401 Unauthorized, 348B, 11ms]
✓  Response status is 401
┌
│ 'Request: ', { nickname: 'new nickname' }
└

↳ login user 1
POST http://arch.homework/sign-in [200 OK, 460B, 80ms]
✓  Response status is 200
✓  Token is present
┌
│ 'Extracted Token:', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwibG9naW4iOiJ1c2VyLTAifQ.7obJyZLfmaOpo7yo4BJK9l2h8gBVJtDUPmC1VRRNzGk'
└

↳ update profile
PUT http://arch.homework/profile [200 OK, 299B, 296ms]
✓  Response status is 200
┌
│ 'Request: ', { nickname: 'new-ickname-0' }
└

↳ get new data of profile
GET http://arch.homework/profile [200 OK, 392B, 51ms]
✓  Response status is 200
✓  nickname equals new-ickname-0
┌
│ 'Response Body: ', { id: 1, login: 'user-0', nickname: 'new-ickname-0' }
└

↳ registration user 2
POST http://arch.homework/sign-up [200 OK, 382B, 45ms]
✓  Response status is 200
┌
│ 'Request: ', { login: 'user-1', password: '123' }
│ 'Response Body: ', { id: 2, login: 'user-1', password: '123' }
└

↳ login user 2
POST http://arch.homework/sign-in [200 OK, 460B, 28ms]
✓  Response status is 200
✓  Token is present
┌
│ 'Extracted Token:', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwibG9naW4iOiJ1c2VyLTEifQ.vLT2tI8fx9pMQ5nNbscGTN9z6CunP6HYTcFuHR2LGQA'
└

↳ get user 2 profile
GET http://arch.homework/profile [200 OK, 385B, 25ms]
✓  Response status is 200
✓  nickname equals user-1
┌
│ 'Response Body: ', { id: 2, login: 'user-1', nickname: 'user-1' }
└

