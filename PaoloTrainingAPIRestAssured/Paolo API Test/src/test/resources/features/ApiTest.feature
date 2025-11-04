Feature: API Test

@smoke @get @regression
Scenario: Verify the status code and response of a GET request
  Given I send a GET request to "https://jsonplaceholder.typicode.com/posts/1"
  Then the response status code should be 200
  And the response should have "userId" with value 1
  And the response should have "body" with value
  """
  quia et suscipit
  suscipit recusandae consequuntur expedita et cum
  reprehenderit molestiae ut ut quas totam
  nostrum rerum est autem sunt rem eveniet architecto
  """

@post @regression
Scenario: Create a new resource using POST request
  Given I send a POST request to "https://jsonplaceholder.typicode.com/posts" with body
  """
  {
    "title": "foo",
    "body": "bar",
    "userId": 1
  }
  """
  Then the response status code should be 201
  And the response should have "title" with value "foo"

@put @regression
Scenario: Update a resource using PUT request
  Given I send a PUT request to "https://jsonplaceholder.typicode.com/posts/1" with body
  """
  {
    "id": 1,
    "title": "foo",
    "body": "bar",
    "userId": 1
  }
  """
  Then the response status code should be 200
  And the response should have "title" with value "foo"

@delete @regression @slow
Scenario: Delete a resource using DELETE request
  Given I send a DELETE request to "https://jsonplaceholder.typicode.com/posts/1"
  Then the response status code should be 200