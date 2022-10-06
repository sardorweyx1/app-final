package uz.weyx.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.weyx.entity.Tag;
import uz.weyx.payload.TagDto;
import uz.weyx.repository.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;


    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag getById(Integer id) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if(tagOptional.isEmpty()) return new Tag();
        return tagOptional.get();
    }

    public List<Tag> getByParam(String name) {
        return tagRepository.getByPartName(name);
    }

    public Tag save(TagDto tagDto) {
        boolean existsByName = tagRepository.existsByName(tagDto.getName());
        if(existsByName) return null;
        Tag tag=new Tag();
        tag.setName(tagDto.getName());
        tagRepository.save(tag);
        return tag;
    }

    public boolean delete(Integer id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if(optionalTag.isEmpty()) return false;
        tagRepository.deleteById(id);
        return true;
    }


    public List<Tag> getTagsByNewsId(Integer newsId) {
        return tagRepository.getTagsByNewsId(newsId);
    }
}
