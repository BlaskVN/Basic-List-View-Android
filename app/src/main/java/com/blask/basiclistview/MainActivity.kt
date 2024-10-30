package com.blask.basiclistview

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var lvNumbers: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view từ file XML
        etNumber = findViewById(R.id.etNumber)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        lvNumbers = findViewById(R.id.lvNumbers)
        tvError = findViewById(R.id.tvError)

        // Xử lý sự kiện khi người dùng nhấn nút Show
        btnShow.setOnClickListener {
            // Xóa thông báo lỗi trước
            tvError.text = ""

            // Lấy dữ liệu từ EditText
            val input = etNumber.text.toString().trim()

            // Kiểm tra dữ liệu nhập có hợp lệ không
            if (input.isEmpty()) {
                tvError.text = "Vui lòng nhập một số!"
                return@setOnClickListener
            }

            val n: Int
            try {
                n = input.toInt()
            } catch (e: NumberFormatException) {
                tvError.text = "Vui lòng nhập một số nguyên dương!"
                return@setOnClickListener
            }

            if (n <= 0) {
                tvError.text = "Vui lòng nhập số lớn hơn 0!"
                return@setOnClickListener
            }

            // Tạo danh sách để hiển thị trong ListView
            val numberList = ArrayList<Int>()

            // Kiểm tra RadioButton được chọn và thêm các số tương ứng
            when {
                rbEven.isChecked -> {
                    // Số chẵn từ 0 đến n
                    for (i in 0..n step 2) {
                        numberList.add(i)
                    }
                }
                rbOdd.isChecked -> {
                    // Số lẻ từ 1 đến n
                    for (i in 1..n step 2) {
                        numberList.add(i)
                    }
                }
                rbSquare.isChecked -> {
                    // Số chính phương từ 0 đến n
                    var i = 0
                    while (i * i <= n) {
                        numberList.add(i * i)
                        i++
                    }
                }
                else -> {
                    tvError.text = "Vui lòng chọn một loại số!"
                    return@setOnClickListener
                }
            }

            // Hiển thị danh sách trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numberList)
            lvNumbers.adapter = adapter
        }
    }
}
