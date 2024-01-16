package com.store.book.Controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.store.book.Dao.BookDao;
import com.store.book.Model.Book;
import com.store.book.Service.BookService;

@RestController
public class BookController {

	@Autowired
	BookDao bookDao;

	@PostMapping("/addBook")
	public ResponseEntity<String> addBook(@RequestBody Book book) {

		Optional<Book> findById = bookDao.findByBookName(book.getBookName());
		if (findById.isPresent()) {
			return new ResponseEntity<String>("Book Already Present", HttpStatus.BAD_REQUEST);
		} else {
			bookDao.save(book);
		}
		return new ResponseEntity<>("Book Added SuccessFully",HttpStatus.OK);

	}

	@GetMapping("/viewAllBooks")
	public List<Book> addBook() {

		List<Book> booksInDatabase = bookDao.findAll();
		return booksInDatabase;
	}

	@GetMapping("/getBooksInfoFromExcel")
//for single request we can use requestparam for file
	public List<Book> readBooksFromExcel(@RequestPart("file") MultipartFile multipartFile,
			@RequestPart("specialBook") Book specialBook) {
		List<Book> books = new ArrayList<>();

		if (!(multipartFile.isEmpty() && multipartFile.getName().endsWith(".xlsx"))) {
			try {
				Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);
				List<Book> booksFromExcel = new ArrayList<>();
				StringBuffer builder = new StringBuffer("Success");

				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next(); // Skip header row

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					Book book = new Book();
					book.setBookId((int) row.getCell(0).getNumericCellValue());
					book.setBookName(row.getCell(1).getStringCellValue());
					book.setBookDescription(row.getCell(2).getStringCellValue());
					book.setPrice(new BigDecimal((int) row.getCell(3).getNumericCellValue()));

					books.add(book);
				}
				books.add(specialBook);
				
				bookDao.saveAll(books);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return books;

	}
}
