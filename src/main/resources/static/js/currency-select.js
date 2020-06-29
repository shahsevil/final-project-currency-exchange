const currencyData = [
  {
    text: "USD - American dollar",
    value: "USD",
    selected: false,
    imageSrc: "./img/usd.png",
  },
  {
    text: "EUR -  European Union",
    value: "EUR",
    selected: false,
    imageSrc: "./img/eur.png",
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
