##Default user for use the api:
- User: defaultUser
- Password: admin234

---

To use the api, you need to have an account created in open weather map to generate an api key(this api consumes the open weather map api). This api was made by me to practice new learnings to the spring framework in general and to challange me

---

##To see another documentation, go to swagger endpoint:
- /swagger-ui/index.html#/

---

You need postgresql database installed to use this api.

##This api uses:
- Flyway for migrations
- PostgreSql for database
- Custom json serialization
- Content negotiation(json or xml)
- HATEOAS(Hypermedia as the engine of application state)
- Swagger(Open Api)
- Authentication with Spring security and JWT

---