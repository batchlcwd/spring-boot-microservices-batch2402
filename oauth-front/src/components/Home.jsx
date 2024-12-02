import axios from "axios";
import React, { useEffect } from "react";
import toast from "react-hot-toast";

const Home = () => {
  const loadScript = (src) => {
    return new Promise((resolve) => {
      //code
      const script = document.createElement("script");
      script.src = src;
      script.onload = () => {
        //success  resolve(true)
        resolve(true);
      };
      script.onerror = () => {
        //fail resolve(false)
        resolve(false);
      };
      document.body.appendChild(script);
    });
  };

  // this method can handle payment

  const handlePayment = async () => {
    console.log("payment handled");

    //create order
    const createOrderApi = `http://localhost:9098/api/v1/orders`;
    const verifyUrl = "http://localhost:9098/api/v1/orders/verify";

    const response = await axios.post(createOrderApi, {
      amount: 2000,
      courseId: "7a6a8041-f8b7-44d5-8f2a-d96bc09765cf",
      userId: "current_user_2352452562",
      userName: "Deepak Lodha",
    });

    toast.success("order created ");

    const createdOrder = response.data;
    console.log(createdOrder);

    //razorpay options
    var options = {
      key: "rzp_test_azfxsO6GTTCccO", // Enter the Key ID generated from the Dashboard
      amount: createdOrder.amount, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
      currency: "INR",
      name: "Minup - LCWD", //your business name
      description: "Learn technical courses online.",
      image:
        "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75",
      order_id: createdOrder.razorpayOrderId, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
      handler: async function (response) {
        //payment success hone par ye call
        toast.success("payment successful");
        console.log(response.razorpay_payment_id);
        console.log(response.razorpay_order_id);
        console.log(response.razorpay_signature);
        // varify paymentapi call//
        const vResponse = await axios.post(verifyUrl, {
          razorpayOrderId: response.razorpay_order_id,
          razorpayPaymentId: response.razorpay_payment_id,
          razorpaySignature: response.razorpay_signature,
        });
        toast.success(vResponse.data);
      },
      prefill: {
        //We recommend using the prefill parameter to auto-fill customer's contact information, especially their phone number
        name: "Aayush Sharma", //your customer's name
        email: "ayush@gmail.com",
        contact: "9000090000", //Provide the customer's phone number for better conversion rates
      },
      notes: {
        address: "Razorpay Corporate Office",
      },
      theme: {
        color: "#3399cc",
      },
    };

    const paymentObject = new window.Razorpay(options);
    paymentObject.on("payment.failed", function (response) {
      toast.error("payment failed");
      console.log(response.error.code);
      console.log(response.error.description);
      console.log(response.error.source);
      console.log(response.error.step);
      console.log(response.error.reason);
      console.log(response.error.metadata.order_id);
      console.log(response.error.metadata.payment_id);
    });
    paymentObject.open();
  };

  // starting

  useEffect(() => {
    loadScript("https://checkout.razorpay.com/v1/checkout.js")
      .then((result) => {
        console.log(result);
        console.log("script loaded");
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <h1>Spring Boot Live batch</h1>
      <p>This batch is going to start from 20th dec 2024.</p>
      <p>Please join us for this exciting event.</p>
      <p>
        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Amet itaque
        est facere placeat similique quasi alias minus deserunt, voluptate
        fugiat fuga aspernatur impedit quibusdam, maiores ipsum autem debitis
        repudiandae? Quo dolorem impedit placeat, dicta deserunt ab. Mollitia
        maxime laborum accusantium reiciendis, quae repudiandae nihil quam
        pariatur at, voluptatem sint odit.
      </p>
      <button onClick={handlePayment}>Pay Now</button>
    </div>
  );
};

export default Home;
