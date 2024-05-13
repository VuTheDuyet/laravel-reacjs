import React, { useState } from 'react';
import axios from 'axios';

export default function UserLogin() {
  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });
  const newUser = {
    username: 'vuduyet203@gmail.com',
    password: '123456'
  };

  const handleChange = e => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      

      const user = newUser;
      console.log(user)


      if (user) {
        if (formData.password !== user.password) {
          user = null;
          

        } else {
          // Lưu ID của người dùng vào localStorage
          localStorage.setItem('userId', "Thế Duyệt");
          // Đăng nhập thành công, điều hướng trang về trang chủ
          window.location.href = "/";
        }

      } else {
        // Hiển thị thông báo lỗi
        
      }
    } catch (error) {
      console.error('Error logging in:', error);
      // Xử lý lỗi ở đây (ví dụ: hiển thị thông báo lỗi)
    }
  };

  return (
    <>
      {/* <!--  ========================= SECTION CONTENT ========================= --> */}
      <section className="section-conten padding-y" style={{ minHeight: 84 }}>
        {/* <!--  ============================ COMPONENT LOGIN   ================================= --> */}
        <div className="card mx-auto" style={{ maxWidth: 380, marginTop: 100 }}>
          <div className="card-body">
            <h4 className="card-title mb-4">Sign in</h4>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <input name="username" className="form-control" placeholder="Username" type="text" onChange={handleChange} />
              </div> {/* <!--  form-group// --> */}
              <div className="form-group">
                <input name="password" className="form-control" placeholder="Password" type="password" onChange={handleChange} />
              </div> {/* <!--  form-group// --> */}

              <div className="form-group">
                <button type="submit" className="btn btn-primary btn-block"> Login  </button>
              </div> {/* <!--  form-group// --> */}
            </form>
          </div> {/* <!--  card-body.// --> */}
        </div> {/* <!--  card .// --> */}

        <p className="text-center mt-4">Don't have account? <a href="/reginster">Sign up</a></p>
        <br></br>
        {/* <!--  ============================ COMPONENT LOGIN  END.// ================================= --> */}
      </section>
      {/* <!--  ========================= SECTION CONTENT END// ========================= --> */}
    </>
  )
}
