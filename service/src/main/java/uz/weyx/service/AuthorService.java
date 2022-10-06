package uz.weyx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.weyx.entity.Author;
import uz.weyx.payload.AuthorDto;
import uz.weyx.repository.AuthorRepository;
import uz.weyx.repository.NewsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final NewsRepository newsRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(Integer id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.orElseGet(Author::new);
    }

    public boolean save(AuthorDto authorDto) {
        boolean existsByName = authorRepository.existsByName(authorDto.getName());
        if (existsByName) return false;
        Author author = new Author();
        author.setName(authorDto.getName());
        authorRepository.save(author);
        return true;
    }

    public boolean edit(Integer id, AuthorDto authorDto) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty())
            return false;
        Author newauthor = optionalAuthor.get();
        newauthor.setName(authorDto.getName());
        authorRepository.save(newauthor);
        return true;
    }

    public boolean delete(Integer id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) return false;
        authorRepository.deleteById(id);
        return true;
    }

    public List<Author> getByParam(String name) {
        return authorRepository.getByPartName(name);
    }

    public Author getByNewsId(Integer newsId) {
//        return newsRepository.findByAuthorId(newsId);

        return authorRepository.getAuthorByNewsId(newsId);
    }
}
