What would I do if I had the time:
- 100% test coverage: all methods of every class (model, repository, service, controller).
- Validation of data coming from frontend and displaying errors (e.g. withdrawal > amount in wallet).
- Exception handling (e.g. wallet not found)
- Wallets should correspond to an account, so the urls would be different (e.g. `users/1234/wallets` instead of `wallets`). It could also be left `wallets` if we had a way to save the logged in user and fetch only her wallets (not optimal to display the user id in url).
