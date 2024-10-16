package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmergencyPhone;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RegisterNumber;
import seedu.address.model.person.Sex;
import seedu.address.model.person.StudentClass;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REGISTER_NUMBER = "1";
    public static final String DEFAULT_SEX = "F";
    public static final String DEFAULT_STUDENT_CLASS = "1A";
    public static final String DEFAULT_EMERGENCY_PHONE = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private RegisterNumber registerNumber;
    private Sex sex;
    private StudentClass studentClass;
    private EmergencyPhone emergencyPhone;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        registerNumber = new RegisterNumber(DEFAULT_REGISTER_NUMBER);
        sex = new Sex(DEFAULT_SEX);
        studentClass = new StudentClass(DEFAULT_STUDENT_CLASS);
        emergencyPhone = new EmergencyPhone(DEFAULT_EMERGENCY_PHONE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        registerNumber = personToCopy.getRegisterNumber();
        sex = personToCopy.getSex();
        studentClass = personToCopy.getStudentClass();
        emergencyPhone = personToCopy.getEmergencyPhone();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code RegisterNumber} of the {@code Person} that we are building.
     */
    public PersonBuilder withRegisterNumber(String registerNumber) {
        this.registerNumber = new RegisterNumber(registerNumber);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Person} that we are building.
     */
    public PersonBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Sets the {@code StudentClass} of the {@code Person} that we are building.
     */
    public PersonBuilder withStudentClass(String studentClass) {
        this.studentClass = new StudentClass(studentClass);
        return this;
    }

    /**
     * Sets the {@code EmergencyPhone} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = new EmergencyPhone(emergencyPhone);
        return this;
    }

    /**
     * Builds a new Person with all the required attributes.
     */
    public Person build() {
        return new Person(name, phone, email, address, registerNumber, sex, studentClass,
                emergencyPhone, tags);
    }

}
