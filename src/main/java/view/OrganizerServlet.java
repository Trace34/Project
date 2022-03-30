package view;

import dao.repositories.EventRepository;
import models.event.Birthday;
import models.event.Event;
import models.event.EventType;
import models.event.Meeting;
import services.EventService;
import services.Service;
import view.mappers.ServletExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrganizerServlet extends HttpServlet {

    private Service<Event> service;
    private ServletExtractor<Event> servletExtractor;

    @Override
    public void init() {
        service = new EventService(new EventRepository());

        servletExtractor = r -> {
            r.setCharacterEncoding("UTF-8");

            EventType type = r.getParameter("gift") != null ? EventType.BIRTHDAY : EventType.MEETING;
            Event event = EventType.getInstance(type);

            String id = r.getParameter("id");

            if (id != null) {
                event.setId(Long.parseLong(id));
            }

            LocalDate date = LocalDate.parse(r.getParameter("date"));
            LocalTime time = LocalTime.parse(r.getParameter("time"));
            String description = r.getParameter("description");

            event.setDate(date);
            event.setTime(time);
            event.setDescription(description);

            switch (type) {

                case BIRTHDAY -> {
                    Birthday birthday = (Birthday) event;

                    String firstName = r.getParameter("firstName");
                    String gift = r.getParameter("gift");

                    birthday.setFirstName(firstName);
                    birthday.setGift(gift);

                    return birthday;
                }

                case MEETING -> {
                    Meeting meeting = (Meeting) event;

                    String firstName = r.getParameter("firstName");
                    String lastName = r.getParameter("lastName");
                    int age = Integer.parseInt(r.getParameter("age"));

                    meeting.setFirstName(firstName);
                    meeting.setLastName(lastName);
                    meeting.setAge(age);

                    return meeting;
                }

                default -> throw new IllegalArgumentException();
            }
        };
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        try {
            switch (path) {
                case "/list" -> list(request, response);
                case "/create" -> create(request, response);
                case "/edit" -> edit(request, response);
                case "/format" -> format(request, response);
                case "/insert" -> insert(request, response);
                case "/update" -> update(request, response);
                case "/remove" -> remove(request, response);
                default -> throw new ServletException();
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setAttribute("events", service.findAll());

        forward(request, response, "list.jsp");
    }

    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("event", null);

        forward(request, response, "format.jsp");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        request.setAttribute("event", service.find(id));

        forward(request, response, "format.jsp");
    }

    private void format(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");
        String selected = request.getParameter("types");

        EventType type = EventType.valueOf(selected.toUpperCase());

        if (id != null) {
            Event event = service.find(Long.parseLong(id));

            if (!event.getType().equals(type)) {
                event = EventType.getInstance(type);
                event.setId(Long.parseLong(id));
            }

            request.setAttribute("event", event);
        } else {
            request.setAttribute("event", EventType.getInstance(type));
        }

        forward(request, response, "%s.jsp".formatted(selected));
    }

    private void insert(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        service.insert(servletExtractor.extract(request));

        response.sendRedirect("list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Event event = servletExtractor.extract(request);

        service.update(event.getId(), event);

        response.sendRedirect("list");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));

        service.remove(id);

        response.sendRedirect("list");
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String jsp)
            throws ServletException, IOException {
        request.getRequestDispatcher(jsp).forward(request, response);
    }
}
