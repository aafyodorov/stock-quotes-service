## Stock quotes service:

<hr>

### Description:
This is a stock quote service that allows you to get stock quotes by a ticker. ~~It also allows you to add stocks to
watch list, and when a certain value is reached, send a notification~~ (_**not implemented**_).

### REST methods:

#### Implemented:

* `GET /iex/quote` - Get quote by a ticker. <br>
  Required parameter:
    * `symbol` - ticker
* `GET /iex/company` - Get  company info by a ticker. <br>
  Required parameter:
  * `symbol` - ticker

#### Not implemented:

* `GET /add` - Add a quote to watch list. <br>
  Required parameters:
    * `user_id` - user's id
    * `symbol` - ticker
    * `exp_price` - expected price.
* `GET /remove` - Remove a quote from watch list. <br>
  Required parameters:
    * `user_id` - user's id
    * `symbol` - ticker
* `GET /update` - Update a quote which is already at watch list. <br>
  Required parameters:
    * `user_id` - user's id
    * `symbol` - ticker
    * `exp_price` - expected price.