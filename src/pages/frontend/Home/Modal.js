import React, { useState } from 'react';
import { IoCloseOutline } from 'react-icons/io5';

function Modal() {
    const [showModal, setShowModal] = useState(true);

    const handleCloseModal = () => {
        setShowModal(false);
    };

    return (
        <>
            {showModal && (
                <div className="modal" data-modal="">
                    <div className="modal-close-overlay" data-modal-overlay="" />
                    <div className="modal-content">
                        <button className="modal-close-btn" data-modal-close="" onClick={handleCloseModal}>
                            <IoCloseOutline/>
                        </button>
                        <div className="newsletter-img">
                            <img
                                src={require("../../../assets/images/newsletter.png")}
                                alt="subscribe newsletter"
                                width={400}
                                height={400}
                            />
                        </div>
                        <div className="newsletter">
                            <form action="#">
                                <div className="newsletter-header">
                                    <h3 className="newsletter-title">Subscribe Newsletter.</h3>
                                    <p className="newsletter-desc">
                                        Subscribe the <b>Anon</b> to get latest products and discount
                                        update.
                                    </p>
                                </div>
                                <input
                                    type="email"
                                    name="email"
                                    className="email-field"
                                    placeholder="Email Address"
                                    required=""
                                />
                                <button type="submit" className="btn-newsletter">
                                    Subscribe
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            )}
          
        </>
    );
}

export default Modal;
