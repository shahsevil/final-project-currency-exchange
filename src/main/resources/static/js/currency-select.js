const currencyData = [
  {
    text: "USD - American dollar",
    value: "USD",
    selected: false,
    imageSrc: "../static/img/usd.png",
  },
  {
    text: "EUR -  European Union",
    value: "EUR",
    selected: false,
    imageSrc: "../static/img/eur.png",
  },
  {
    text: "CNY - Chinese Yuan",
    value: "CNY",
    selected:false,
    imageSrc:"../static/img/cny.png"
  },
  {
    text: "CAD - Canadian Dollar",
    value: "CAD",
    selected:false,
    imageSrc:"../static/img/cad.png"
  },
  {
    text: "HKD - Hong Kong Dollar",
    value: "HKD",
    selected:false,
    imageSrc:"../static/img/hkd.png"
  },
  {
    text: "ISK - Icelandic Króna",
    value: "ISK",
    selected:false,
    imageSrc:"../static/img/isk.png"
  },
  {
    text: "BGN - Bulgarian Lev",
    value: "BGN",
    selected:false,
    imageSrc:"../static/img/bgn.png"
  },
  {
    text: "PHP - Philippine Peso",
    value: "PHP",
    selected:false,
    imageSrc:"../static/img/php.png"
  },
  {
    text: "GBP - British Pound Sterling",
    value: "GBP",
    selected:false,
    imageSrc:"../static/img/gbp.png"
  },
  {
    text: "RUB - Russian Ruble",
    value: "RUB",
    selected:false,
    imageSrc:"../static/img/rub.png"
  },
];

$("#exchange-from").ddslick({
  data: currencyData,
  width: "100%",
  selectText: "",
  imagePosition: "left",
  background: "transparent",
  onSelected: function (selectedData) {
    //callback function: do something with selectedData;
  },
});

$("#exchange-to").ddslick({
  data: currencyData,
  width: "100%",
  selectText: "",
  imagePosition: "left",
  background: "transparent",
  onSelected: function (selectedData) {
    //callback function: do something with selectedData;
  },
});
