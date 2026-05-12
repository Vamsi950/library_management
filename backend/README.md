Library backend quick-start and sample requests

Seed data: the application will load sample books and members when started (H2 memory DB) from `src/main/resources/data.sql`.

Sample curl requests:

Create a book:

```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"My Book","author":"An Author"}'
```

List books:

```bash
curl http://localhost:8080/api/books
```

Issue a book (bookId, memberId):

```bash
curl -X POST http://localhost:8080/api/issues/issue/1/1
```

Return a book (issueId):

```bash
curl -X PUT http://localhost:8080/api/issues/return/1
```

Get issues for a member:

```bash
curl http://localhost:8080/api/issues/member/1
```
