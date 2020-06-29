$("#date-from")
  .datepicker({
    dateFormat: "dd MM",
    beforeShow: function () {
      // $("#ui-datepicker-div")
      //   .removeClass("datepicker-right")
      //   .addClass("datepicker-left");
    },
    onSelect: function () {
      const dateObject = $(this).datepicker("getDate");
      const month = dateObject.toLocaleDateString("en-US", { month: "long" });
      const day = dateObject.getDate();
      $("#date-from-value").html(
        `<span class="datepicker-value-date">${day}</span><span class="datepicker-value-month">${month}</span>`
      );
    },
  })
  .position({ my: "center" });
$("#date-to").datepicker({
  dateFormat: "dd MM",
  beforeShow: function () {
    $("#ui-datepicker-div");
  },
  onSelect: function () {
    const dateObject = $(this).datepicker("getDate");
    const month = dateObject.toLocaleDateString("en-US", { month: "long" });
    const day = dateObject.getDate();

    $("#date-to").val("").attr("placeholder", "");

    $("#date-to-value").html(
      `<span class="datepicker-value-date">${day}</span><span class="datepicker-value-month">${month}</span>`
    );
  },
});

window.addEventListener("DOMContentLoaded", () => {
  const today = new Date();
  $("#date-from-value, #date-to-value").html(
    `<span class="datepicker-value-date">${today.getDate()}</span><span class="datepicker-value-month">${today.toLocaleDateString(
      "en-US",
      { month: "long" }
    )}</span>`
  );
});
